package edu.famu.myinvestments.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public
class PostService {

    public Post getPostById(String id)throws ExecutionException, InterruptedException{
        Post post = null;
        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference postRef = db.collection("Posts").document(id);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = postRef.get();
        //Retrieve document
        DocumentSnapshot document = future.get();

        //Convert JSON into Post class object
        if(document.exists())
        {
            UserService service = new UserService();

            DocumentReference userRef = (DocumentReference) document.get("author");
            ApiFuture<DocumentSnapshot> userQuery = userRef.get();
            DocumentSnapshot userDoc = userQuery.get();
            User user = userDoc.toObject(User.class);

            post =  new Post(
                    document.getId(),
                    document.getString("title"),
                    document.getString("contents"),
                    document.getLong("likes"),
                    (ArrayList<String>)document.get("tags"),
                    document.getBoolean("showComments"),
                    (document.getTimestamp("updatedAt").toDate()),
                    ((document.getTimestamp("createdAt").toDate())), user);
        }


        return post;

    }

    public Comment getCommentById(String id)throws ExecutionException, InterruptedException{
        Comment comment = null;
        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference commentRef = db.collection("Comment").document(id);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = commentRef.get();
        //Retrieve document
        DocumentSnapshot document = future.get();

        //Convert JSON into Post class object
        if(document.exists())
        {
            UserService service = new UserService();
            PostService postService = new PostService();

            DocumentReference userRef = (DocumentReference) document.get("user");
            ApiFuture<DocumentSnapshot> userQuery = userRef.get();
            DocumentSnapshot userDoc = userQuery.get();
            User user = userDoc.toObject(User.class);

            DocumentReference postRef = (DocumentReference) document.get("post");
            ApiFuture<DocumentSnapshot> postQuery = postRef.get();
            DocumentSnapshot postDoc = postQuery.get();
            Post post = postDoc.toObject(Post.class);

            comment =  new Comment(
                    document.getId(),
                    document.getString("content"),
                    document.getLong("likes"),
                    user, post);
        }


        return comment;

    }

    public String createPost(RestPost post) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> postRef = db.collection("Posts").add(post);
        return postRef.get().getId();
    }

    public List<Comment> getPostComments(String id)throws ExecutionException, InterruptedException{

        Post post = getPostById(id);

        if(post != null)
        {
            List<Comment> comments = new ArrayList();
            //database connection object
            Firestore db = FirestoreClient.getFirestore();

            //retrieves a reference to the document(row) of the collection (table) with a specific id
            DocumentReference postRef = db.collection("Posts").document(id);

            //Query for post by post
            Query commentQuery = db.collectionGroup("Comments").whereEqualTo("post", postRef);
            ApiFuture<QuerySnapshot> querySnapshot = commentQuery.get();

            //loop over results and create Comment objects
            for(DocumentSnapshot document : querySnapshot.get().getDocuments())
            {
                User user;

                DocumentReference userRef = (DocumentReference) document.get("user");
                //ApiFuture allows us to make async calls to the database
                ApiFuture<DocumentSnapshot> future = userRef.get();
                //Retrieve document
                DocumentSnapshot userDoc = future.get();

                if(userDoc.exists())
                    user = userDoc.toObject(User.class);
                else
                {
                    user = new User();
                    user.setFirstName("Unknown");
                    user.setLastName("User");
                }

                //add the comment to the list
                comments.add(new Comment(document.getId(),
                        document.getString("content"),
                        document.getLong("likes"),
                        user,
                        post));
            }

            return comments;
        }
        return null;
    }

    public String createComment(RestComment comment) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> commentRef = db.collection("Comments").add(comment);
        return commentRef.get().getId();
    }


    public Boolean deletePostComment(String id) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = db.collection("Comments").document(id).delete();
        if( writeResult.get().getUpdateTime() != null)
            return true;

        return false;
    }
}

