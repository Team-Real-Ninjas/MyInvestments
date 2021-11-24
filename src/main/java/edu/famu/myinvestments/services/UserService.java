package edu.famu.myinvestments.services;



import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
//SERVICE CLASSES... QUERY TO THE DATABASE... OR CHANGE/MANIPULATE THE DATA

@Service
public class UserService {

    public List<Post> getPostByUserId(String id) throws ExecutionException, InterruptedException {

        List<Post> posts = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference userRef = db.collection("User").document(id);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        //Convert JSON into User class object
        User user = userDoc.toObject(User.class);

        //Query for post by user
        Query postQuery = db.collectionGroup("Post").whereEqualTo("author", userRef);
        ApiFuture<QuerySnapshot> querySnapshot = postQuery.get();

        //loop over results and create Post objects
        for(DocumentSnapshot document : querySnapshot.get().getDocuments())
        {
            posts.add(new Post(
                    document.getId(),
                    document.getString("title"),
                    document.getString("contents"),
                    document.getLong("likes"),
                    (ArrayList<String>)document.get("tags"),
                    document.getBoolean("showComments"),
                    (document.getTimestamp("updatedAt").toDate()),
                    ((document.getTimestamp("createdAt").toDate())), user));
        }

        return posts;
    }
}
