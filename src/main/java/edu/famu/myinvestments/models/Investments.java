package edu.famu.myinvestments.models;

import com.google.cloud.Timestamp;

import java.util.Date;

public class Investments extends BaseInvestments {
        private User createdBy;

        public Investments(){

        }
        public Investments(String id, User createdBy, String type,
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
