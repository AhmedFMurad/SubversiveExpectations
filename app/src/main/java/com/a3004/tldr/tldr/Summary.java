package com.a3004.tldr.tldr;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Summary {
    private ArrayList<String> upvotes;
    private ArrayList<String> downvotes;
    private String UID;
    private String content;
    private String url;
    public Summary() {

    }

    public ArrayList<String> getUpvotes() {
        return upvotes;
    }

    public ArrayList<String> getDownvotes() {
        return downvotes;
    }

    public String getUID() {
        return UID;
    }

    public String getContent() {
        return content;
    }

    public void setUpvotes(ArrayList<String> upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(ArrayList<String> downvotes) {
        this.downvotes = downvotes;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
