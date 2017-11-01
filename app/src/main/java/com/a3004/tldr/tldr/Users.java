package com.a3004.tldr.tldr;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Users {
    protected String username;
    protected String id;
    protected ArrayList<String> preferedCategories;
    protected int amountOfCategories;
    protected int points;
    //protected ArrayList<String> prizes;
    protected int amountOfPrizes;

    public Users() {

    }

    public Users(String username, String id, int amountOfCategories, int points, int amountOfPrizes) {
        this.username = username;
        this.id = id;
        this.amountOfCategories = amountOfCategories;
        this.points = points;
        this.amountOfPrizes = amountOfPrizes;
    }

    public Users(String username, String id) {
        this.username = username;
        this.id = id;
    }

    public Users(String username, String id, ArrayList<String> preferedCategories, int amountOfCategories, int points, int amountOfPrizes) {
        this.username = username;
        this.id = id;
        this.preferedCategories = preferedCategories;
        this.amountOfCategories = amountOfCategories;
        this.points = points;
        this.amountOfPrizes = amountOfPrizes;
    }
    /*public void addCategory(String category){
        if(!(this.preferedCategories.length < this.amountOfCategories)){
            // expand the array
        } else {
            this.preferedCategories[this.amountOfCategories] = category;
            this.amountOfCategories++;
        }
        // Update database?
    }

    public void removeCategory(String category){
        int index = Arrays.asList(this.preferedCategories).indexOf(category);
        for(int i=index; i<this.amountOfCategories;i++){
            this.preferedCategories[i] = this.preferedCategories[i+1];
        }
        this.amountOfCategories--;
        this.preferedCategories[this.amountOfCategories] = null;
    }
    public boolean chooseNewUsername(String newUsername){
        // Check if database has username already
        if(true){
            return false;
        } else {
            this.username = newUsername;
            // Update database here
            return true;
        }
    }

    public boolean submitSummary(int articleID, String summary){
        // do stuff here, should probably include the article object so we can edit those.
        return true;
    }

    public boolean redeemPrizes(int prizeID){
        // Get the their points from the database and if they can afford, if not, return false
        this.points = this.points - prizeID;
        this.prizes[this.amountOfPrizes] = prizeID;
        //update database
        return true;
    }

    public void loadArticles(){
        // we have this.preferedCategories, use those to load the articles
        // We will probably need to include the categories and/or articles objects here so we can load them to the UI
    }*/


}
