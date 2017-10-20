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
    public String getArticleText(){return articleText;}
    public String getArticleURL(){return articleURL;}
    public String getArticleImage(){return articleImage;}
    public String getArticleCategory(){return articleCategory;}
    public int getArticleID(){return articleID;}
    public Summary[] getAllSummaries(){
        return this.summariesArray;
    }


}
