package edu.famu.myinvestments.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.Comment;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.RestPost;
import edu.famu.myinvestments.models.User;
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

    public List<Comment> getPostComments(String id)throws ExecutionException, InterruptedException{

        Post post = getPostById(id);

        if(post != null)
        {
            List<Comment> comments = new ArrayList<>();
            //database connection object
            Firestore db = FirestoreClient.getFirestore();

            //retrieves a reference to the document(row) of the collection (table) with a specific id
            DocumentReference postRef = db.collection("Post").document(id);

            //Query for post by post
            Query commentQuery = db.collectionGroup("Comment").whereEqualTo("post", postRef);
            ApiFuture<QuerySnapshot> querySnapshot = commentQuery.get();

            //loop over results and create Comment objects
            for(DocumentSnapshot document : querySnapshot.get().getDocuments())
            {
                User author;

                DocumentReference authorRef = (DocumentReference) document.get("author");
                //ApiFuture allows us to make async calls to the database
                ApiFuture<DocumentSnapshot> future = authorRef.get();
                //Retrieve document
                DocumentSnapshot authorDoc = future.get();

                if(authorDoc.exists())
                    author = authorDoc.toObject(User.class);
                else
                {
                    author = new User();
                    author.setUsername("unknown");
                }

                //add the comment to the list
                comments.add(new Comment(document.getId(),
                        document.getString("content"),
                        document.getLong("likes"), author, post));
            }

            return comments;
        }
        return null;
    }

    public String createPost(RestPost post) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> postRef = db.collection("Post").add(post);
        return postRef.get().getId();
    }
}

