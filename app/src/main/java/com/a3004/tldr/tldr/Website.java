package com.a3004.tldr.tldr;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.y;

public class Website {
    private Map<String, String> feeds = new HashMap<>();
    private String siteTitle;


    public Website() {

    }

    public Website(String title, Map<String, String> feed) {
        feeds = feed;
        siteTitle = title;
    }

    public Map<String, String> getFeeds() {
        return this.feeds;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void addFeed(String category, String url) {
        feeds.put(category, url);
    }

}