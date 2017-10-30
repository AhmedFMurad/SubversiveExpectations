package com.a3004.tldr.tldr;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Website {

    private Map<String, String> feeds = new HashMap<>();
    private String siteTitle;

    public Website() {

    }
    public Website(String title, Map<String,String> feed){
        feeds = feed;
        siteTitle = title;
    }

    public Map<String, String> getFeeds(){
        return this.feeds;
    }


    public void addFeed(String category, String url){
        feeds.put(category, url);
    }

    public static void main (String[] args){
        DatabaseReference databaseWebsites;
        databaseWebsites = FirebaseDatabase.getInstance().getReference();

        Map<String, String> tmpFeed = new HashMap<>();
        String title = "cnn";
        Website newWeb = new Website(title, tmpFeed);
        newWeb.addFeed("Top Stories", "http://rss.cnn.com/rss/cnn_topstories.rss");
        newWeb.addFeed("World", "http://rss.cnn.com/rss/cnn_world.rss");

        databaseWebsites.setValue(newWeb);
        System.out.println("hello\n");
    }
}