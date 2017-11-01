package com.a3004.tldr.tldr;


import java.util.ArrayList;

public class Article {
    private String articleText;
    private String articleImage;
    private String articleURL;
    private String articleCategory;
    //private static int articleID = 0;
    private String articleTitle;
    private String articleDescription;
    private ArrayList<Summary> summaries;

    public Article() {

    }
    public Article(String articleTitle, String articleText, String articleImage, String articleURL,
                   String articleCategory) {
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.articleImage = articleImage;
        this.articleURL = articleURL;
        this.articleCategory = articleCategory;
        //this.articleID++;
        //
    }

    public Article(String articleCategory, String articleText) {
        this.articleCategory = articleCategory;
        this.articleText = articleText;
        //this.articleID = articleID;
    }

    public Article(String articleURL, String articleTitle, String articleDescription){
        this.articleDescription = articleDescription;
        this.articleURL = articleURL;
        this.articleTitle = articleTitle;
        //this.articleID++;
    }

    public void displayText(){
        if (summaries.isEmpty() == true){
            //display descriptions

        } else {
            //show summaries
        }
    }

    //setters
    public void setArticleText(String text){
        articleText = text;
    }
    public void setArticleImage(String text){
        articleImage = text;
    }
    public void setArticleURL(String text){
        articleURL = text;
    }

    //getters
    public String getArticleText() { return articleText; }
    public String getArticleURL() { return articleURL; }
    public String getArticleImage() { return articleImage; }
    public String getArticleCategory() { return articleCategory; }
    //public int getArticleID() { return articleID; }
    public ArrayList<Summary> getAllSummaries(){
        return this.summaries;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }
}
