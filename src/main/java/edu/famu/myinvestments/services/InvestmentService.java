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
public class InvestmentService {

        public Investments getInvestmentById(String id)throws ExecutionException, InterruptedException{
            Investments Investment = null;
            //database connection object
            Firestore db = FirestoreClient.getFirestore();

            //retrieves a reference to the document(row) of the collection (table) with a specific id
            DocumentReference invRef = db.collection("Investments").document(id);

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> future = invRef.get();
            //Retrieve document
            DocumentSnapshot document = future.get();

            //Convert JSON into Investment class object
            if(document.exists())
            {
                InvestmentService service = new InvestmentService();

                //reference User Document
                DocumentReference userRef = (DocumentReference) document.get("createdBy");
                ApiFuture<DocumentSnapshot> userQuery = userRef.get();
                DocumentSnapshot userDoc = userQuery.get();
                User createdBy = userDoc.toObject(User.class);

                Investment =  new Investments (
                        document.getId(),
                        createdBy,
                        document.getString("type"),
                        document.getString("comment"),
                        document.getDouble("purchaseAmount"),
                        document.getDouble("stockAmount"),
                        (document.getTimestamp("updatedAt").toDate()),
                        ((document.getTimestamp("createdAt").toDate())));
            }


            return Investment;

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

        public String createInvestment(RestInvestments investment) throws ExecutionException, InterruptedException{
            //database connection object
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentReference> invRef = db.collection("Investments").add(investment);
            return invRef.get().getId();
        }
}
