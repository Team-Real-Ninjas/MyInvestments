package edu.famu.myinvestments.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NewsResponse extends NResponse {
        ArrayList<News> results;
    }

