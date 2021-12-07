package edu.famu.myinvestments.models;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class RestComment extends BaseComment {
    private DocumentReference user;
    private DocumentReference post;

    public RestComment(){

    }

    public RestComment(String id, String content, long likes, DocumentReference user, DocumentReference post) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.user = user;
        this.post = post;
    }

    public DocumentReference getUser() {
        return user;
    }

    public void setUser(String user) {
        Firestore db = FirestoreClient.getFirestore();
        this.user = db.collection("User").document(user);
    }

    public DocumentReference getPost() {
        return post;
    }

    public void setPost(String post) {
        Firestore db = FirestoreClient.getFirestore();
        this.post = db.collection("Posts").document(post);
    }
}