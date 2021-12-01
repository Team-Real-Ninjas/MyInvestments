package edu.famu.myinvestments.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Date;

public class RestInvestments extends BaseInvestments {
        private DocumentReference createdBy;

        public RestInvestments(){

        }
        public RestInvestments(String id, DocumentReference createdBy, String type,
                               String comment, Number purchaseAmount, Number stockAmount,
                               Date createdAt, Date updatedAt ){
            this.id = id;
            this.createdBy = createdBy;
            this.type = type;
            this.comment = comment;
            this.purchaseAmount = purchaseAmount;
            this.stockAmount = stockAmount;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

    public RestInvestments(String id,  String createdBy,String type,
                           String comment, Number purchaseAmount, Number stockAmount,
                           Date createdAt, Date updatedAt){
        this.id = id;
        this.setCreatedBy(createdBy);
        this.type = type;
        this.comment = comment;
        this.purchaseAmount = purchaseAmount;
        this.stockAmount = stockAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DocumentReference getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        Firestore db = FirestoreClient.getFirestore();
        this.createdBy = db.collection("User").document(createdBy);
    }

}


