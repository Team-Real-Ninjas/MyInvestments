package edu.famu.myinvestments.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class News {

    private String article_url,
            author,
            description,
            id,
            image_url;

    private ArrayList<String> keywords;
    //protected Date published_utc;


}
