package com.a3004.tldr.tldr;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.R.attr.data;

public class Accounts extends Users {
// NO LONGER IN USE.
    protected int points;
    protected int[] prizes;
    protected int amountOfPrizes;

    public Accounts(){

    }

    public Accounts(int points, int[] prizes, int amountOfPrizes) {
        this.points = points;
        this.prizes = prizes;
        this.amountOfPrizes = amountOfPrizes;
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
    }

}
