package edu.famu.myinvestments.models;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.Date;

public class RestPost extends BasePost {
    private DocumentReference author;

    public RestPost(){

    }

    public RestPost(String id, String title, String content, ArrayList<String> tags,
                    boolean showComments, long likes, Date createdAt, Date updatedAt,
                    DocumentReference author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.showComments = showComments;
        this.likes = likes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public RestPost(String id, String title, String content,
                    ArrayList<String> tags, boolean showComments, long likes,
                    Date createdAt, Date updatedAt, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.showComments = showComments;
        this.likes = likes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.setAuthor(author);
    }

    public DocumentReference getAuthor() {
        return author;
    }

    //Connects document reference to database
    //Needed for updating
    public void setAuthor(String author) {
        Firestore db = FirestoreClient.getFirestore();
        this.author = db.collection("User").document(author);
    }

}
