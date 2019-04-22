package com.example.the_universal_telegram;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;


public class Article implements Serializable {

    private static final boolean DEBUG = true;

    private static final String TAG_PREFIX = "Articles";

    private String articles;
    private String publisher;
    private String title;
    private String description;
    private String url;
    private String urlTOImage;
    private String publishedAt;
    private String content;

    public Article(String articles)
    {



    }


}
