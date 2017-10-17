package com.a3004.tldr.tldr;

import java.util.Map;

public class Website {
    public Map<String, String> rssArray;
    private String siteTitle;

    Website(String title){
        siteTitle = title;
    }

    public void fetchArticles(){
        // This should be on a timer to fetch news automatically or something
    }


}