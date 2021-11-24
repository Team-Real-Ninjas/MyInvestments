package edu.famu.myinvestments.models;

import com.google.cloud.firestore.annotation.DocumentId;

//Template for other comment classes -- Parent Class
public class BaseComment {

    //What is this?
    @DocumentId
    //PROTECTED means: Private to the public, BUT ALLOWS for inheritance with Children classes
    protected String id;
    protected String content;
    protected long likes;


    //Creating basic set and get functions for the Comment Class
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLike() {
        return likes;
    }

    public void setLike(long likes) {
        this.likes = likes;
    }


}
