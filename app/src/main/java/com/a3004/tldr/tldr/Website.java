package com.a3004.tldr.tldr;

import java.util.HashMap;
import java.util.Map;

public class Website {
    private Map<String, String> feeds = new HashMap<>();
    private String siteTitle;

    Website(String title){
        siteTitle = title;
    }

    public Map<String, String> getFeeds(){
        return this.feeds;
    }

    public void fetchArticles(){
        // This should be on a timer to fetch news automatically or something
    }

    public void addFeed(String category, String url){
        feeds.put(category, url);
    }
}