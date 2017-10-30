package com.a3004.tldr.tldr;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.R.attr.data;

public class Accounts extends Users {
    private String password;
    protected int points;
    protected int[] prizes;
    protected int amountOfPrizes;
    // This might be needed in main activity instead..
    private String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Accounts(){

    }
    public boolean login(String username, String password){
        // Check database for username in if statement below
        if(true){
            // Get user's password from the database
            if(md5(password).equals(this.password)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
