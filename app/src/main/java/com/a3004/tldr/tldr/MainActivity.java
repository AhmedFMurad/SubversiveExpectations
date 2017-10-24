package com.a3004.tldr.tldr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //private MobileServiceClient mClient;

    // --- recycler view part ---
    private RecyclerView mRecyclerView;
    private ArrayList<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity); // should be activity_main

        // added stuff from here



        // --- recycler view part ---
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initData();
        //initAdapter();

    }

    // --- recycler view part ---

    private void initData() {
        articles = new ArrayList<>();

        // add articles here
        // need to link to a website
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
    }

    // --- recycler view part ---
    private void initAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(articles);
        mRecyclerView.setAdapter(adapter);
    }
}