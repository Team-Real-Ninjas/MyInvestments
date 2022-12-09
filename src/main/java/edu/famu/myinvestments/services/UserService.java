package edu.famu.myinvestments.services;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.Investments;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.RestPost;
import edu.famu.myinvestments.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
//SERVICE CLASSES... QUERY TO THE DATABASE... OR CHANGE/MANIPULATE THE DATA

@Service
public class UserService {

    //GETS ALL USERS
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        List<User> userList = new ArrayList();

        ApiFuture<QuerySnapshot> queryUser = db.collection("User").get();


        for (DocumentSnapshot doc : queryUser.get().getDocuments()) {

            //DOESN'T HAVE ANY REFERENCES OUTSIDE OF THE CLASS
            //PULLS Data from database and convert JSON to the Class object
            userList.add(doc.toObject(User.class));

                    /*
                    (doc.getString("employeeId"), doc.getString("firstName"),
                    doc.getString("lastName"), doc.getString("phoneNumber"),
                    doc.getBoolean("approver"), doc.getBoolean("active")));
                    */
        }

        return userList;
    }

    //GET USERS POST
    public List<Post> getPostByUserId(String id) throws ExecutionException, InterruptedException {

        List<Post> posts = new ArrayList();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference userRef = db.collection("User").document(id);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        //Convert JSON into User class object
        User author = userDoc.toObject(User.class);

        //Query for posts by user
        Query postQuery = db.collectionGroup("Posts").whereEqualTo("author", userRef);
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
                    ((document.getTimestamp("createdAt").toDate())),
                    author));
        }

        return posts;
    }
    public List<Investments> getInvestmentByUserId(String id)throws ExecutionException, InterruptedException{

        List<Investments> investments = new ArrayList();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference userRef = db.collection("User").document(id);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        //Convert JSON into User class object
        User createdBy = userDoc.toObject(User.class);

        //Query for investments by user
        Query invQuery = db.collectionGroup("Investments").whereEqualTo("createdBy", userRef);
        ApiFuture<QuerySnapshot> querySnapshot = invQuery.get();

        //loop over results(List) and create Investment objects
        for(DocumentSnapshot document : querySnapshot.get().getDocuments())
        {

            investments.add( new Investments (
                    document.getId(),
                    createdBy,
                    document.getString("type"),
                    document.getString("comment"),
                    document.getDouble("purchaseAmount"),
                    document.getDouble("stockAmount"),
                    (document.getTimestamp("updatedAt").toDate()),
                    ((document.getTimestamp("createdAt").toDate()))));
        }


        return investments;

    }

    public User getUserByID(String id) throws ExecutionException, InterruptedException {
        // DB Connection Object
        Firestore db = FirestoreClient.getFirestore();

        //DocumentReference refers to a document location in a Firestore
        //database and can be used to write, read, or listen to the location.
        //Retrieves a reference to the document(row) of the collection (table) with specified ID
        DocumentReference userRef = db.collection("User").document(id);

        //ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve snapshot document
        DocumentSnapshot document = (DocumentSnapshot) userRef.get().get();

        //Convert to User Object
        return(document.toObject(User.class));
    }

    public String createUser(User user) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> userRef = db.collection("User").add(user);
        return userRef.get().getId();
    }

    /*
    // HOWWC DO YOU CALL UPDATE FUNCTION IN CONTROLLER
    // HOWWC DO I MAKE A REST USER CLASS

    public RestUser updateUserProfileImage(String num) throws ExecutionException, InterruptedException {
        ObjectMapper mapObject = new ObjectMapper();
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference userRef = db.collection("User").document(id)
        DocumentSnapshot docs = (DocumentSnapshot) userRef.get().get();

        //ApiFuture allows uss to make async calls to database
        ApiFuture<QuerySnapshot> futurepNum = query.get();
        List<QueryDocumentSnapshot> docs = futurepNum.get().getDocuments();


        if(docs.size() > 0){
            DocumentReference doc = docs.get(0).getReference();
            //String refers to value name ... Object is what we are passing into it
            Map<String, Object> update = new HashMap<>();
            update.put("profileImage", user.getProfileImage());

            //Async Document Update
            ApiFuture<WriteResult> writeResult = doc.update(update);
        }
        return user;
    }
 */

}
