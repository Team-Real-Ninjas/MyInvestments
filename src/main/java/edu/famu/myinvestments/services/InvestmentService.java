package edu.famu.myinvestments.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                        ((document.getTimestamp("createdAt"))));
            }


            //RETURNS A NEW INVESTMENT OBJECT BASED OFF THE INPUT ID
            return Investment;

        }

        public String createInvestment(RestInvestments investment) throws ExecutionException, InterruptedException{
            //database connection object
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentReference> invRef = db.collection("Investments").add(investment);
            return invRef.get().getId(); //RETURNS THE ID FROM THE INVESTMENT DOCUMENT CREATED
        }

        //HOWWC IS THIS CORRECT?
        public RestInvestments updateInvestment(RestInvestments investments) throws ExecutionException, InterruptedException {
            //ObjectMapper mapObject = new ObjectMapper();
            Firestore db = FirestoreClient.getFirestore();
            Query query = db.collection("Investments").whereEqualTo("id", investments.getId());

            //ApiFuture allows uss to make async calls to database
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> docs = future.get().getDocuments();

            // DocumentSnapshot doc = (DocumentSnapshot) query.get();

            if(docs.size() > 0){

                //Gets the returned document reference as variable "doc"
                DocumentReference doc = docs.get(0).getReference();
                //String refers to the Key value in the MAP.
                // NOTE:  A DOCUMENT REFERENCE ALL KEYS(VALUE NAMES) ARE STRINGS
                // ... Object is what we are passing into the mapped
                Map<String, Object> update = new HashMap<>();

               // update.put("id", investments.getId());
               // update.put("createdBy", investments.getCreatedBy());
                update.put("type", investments.getType());
                update.put("comment", investments.getComment());
                update.put("purchaseAmount", investments.getPurchaseAmount());
                update.put("stockAmount", investments.getStockAmount());
                update.put("createdAt", investments.getCreatedAt());
              //  update.put("createdBy", investments.getCreatedBy());

                //Async Document Update
                ApiFuture<WriteResult> writeResult = doc.update(update);
            }
            return investments;
        }
}
