package com.a3004.tldr.tldr;


public class Article {
    protected String articleText;
    protected String articleImage;
    protected String articleURL;
    protected String articleCategory;
    protected int articleID;
    protected Summary[] summariesArray;

    public Summary[] getAllSummaries(){
        return this.summariesArray;
    }
}
