package com.a3004.tldr.tldr;


public class Article {
    String articleText;
    String articleImage;
    String articleURL;
    String articleCategory;
    int articleID;
    Summary[] summariesArray;

    public Article(String articleText, String articleImage, String articleURL,
                   String articleCategory, int articleID) {
        this.articleText = articleText;
        this.articleImage = articleImage;
        this.articleURL = articleURL;
        this.articleCategory = articleCategory;
        this.articleID = articleID;
    }

    public Article(String articleCategory, String articleText, int articleID) {
        this.articleCategory = articleCategory;
        this.articleText = articleText;
        this.articleID = articleID;
    }

    public Summary[] getAllSummaries(){
        return this.summariesArray;
    }
}
