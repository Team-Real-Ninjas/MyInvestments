package edu.famu.myinvestments.models;

//Child of BaseComment Class
public class Comment extends BaseComment {
    private User user;
    private Post post;

    public Comment(){

    }

    public Comment(String id, String content, long likes, User user, Post post) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}