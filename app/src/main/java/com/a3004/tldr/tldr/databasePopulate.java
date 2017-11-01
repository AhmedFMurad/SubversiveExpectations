package com.a3004.tldr.tldr;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class databasePopulate {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Category cat;
    public void initFirebase(){
        // we need to initilize this for it to work, but can't initialize it.
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("categories");
    }
    public static void main(String args[]) {

        databasePopulate populate = new databasePopulate();
        populate.cat = new Category();
        //populate.initFirebase();
        // this works, but can't add it to the database.
        populate.cat.parseXML("http://rss.nytimes.com/services/xml/rss/nyt/World.xml");
        ArrayList<Article> articles;
        articles = populate.cat.getArticles();
        System.out.println(articles.size());
        System.out.println(articles.get(10).getArticleDescription());
        System.out.println(articles.get(10).getArticleURL());
       // populate.mDatabaseReference.child("articles").setValue(populate.cat.getArticles().size());
    }

}

