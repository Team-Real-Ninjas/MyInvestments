package edu.famu.myinvestments.models;


import java.util.ArrayList;
import java.util.Date;

public class Post extends BasePost {
    private User author;

    public Post(){

    }

    public Post(String id, String title, String content,
                    long likes, ArrayList<String> tags, boolean showComments,
                    Date createdAt, Date updatedAt, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.tags = tags;
        this.showComments = showComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = user;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

}
