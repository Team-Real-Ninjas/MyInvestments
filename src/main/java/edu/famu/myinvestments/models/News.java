package edu.famu.myinvestments.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class News {

    @DocumentId
    protected String id;
    protected String article_url,
            author,
            description,
            image_url,
            publisher;

    protected ArrayList<String> keywords;


    public News() {
    }

    public News(String id, String article_url, String author,
                String description, String image_url,
                String publisher, ArrayList<String> keywords) {
        this.id = id;
        this.article_url = article_url;
        this.author = author;
        this.description = description;
        this.image_url = image_url;
        this.publisher = publisher;
        this.keywords = keywords;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }
}
