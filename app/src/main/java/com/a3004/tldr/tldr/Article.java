package com.a3004.tldr.tldr;


public class Article {
    private String articleText;
    private String articleImage;
    private String articleURL;
    private String articleCategory;
    private int articleID;
    private Summary[] summariesArray;

    public Summary[] getAllSummaries(){
        return this.summariesArray;
    }
}
