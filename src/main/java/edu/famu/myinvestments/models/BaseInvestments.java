package edu.famu.myinvestments.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseInvestments {
    @DocumentId
    protected String id;
    protected String type;
    protected String comment;
    protected Number purchaseAmount;
    protected Number stockAmount;
    protected Date createdAt;
    protected Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Number getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Number purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Number getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Number stockAmount) {
        this.stockAmount = stockAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Timestamp createdAt)   {
        this.createdAt = createdAt.toDate();
    }

    public Date getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt){
        this.updatedAt = updatedAt.toDate();
    }

}
