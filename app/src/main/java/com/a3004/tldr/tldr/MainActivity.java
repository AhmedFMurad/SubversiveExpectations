package com.a3004.tldr.tldr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.microsoft.windowsazure.mobileservices.*;


public class MainActivity extends AppCompatActivity {
    //private MobileServiceClient mClient;

    private RecyclerView mRecyclerView;
    private List<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        // added stuff from here
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        initData();
        initAdapter();
/*
        try{
            mClient = new MobileServiceClient(
                    "https://tldrapp.azurewebsites.net",
                    this
            );
        } catch (Exception e){
            e.printStackTrace();
        }
*/
    }

    private void initData() {
        articles = new ArrayList<>();

        // add articles here
        // need to link to a website
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News AGAIN", R.drawable.pepe2));
    }

    private void initAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(articles);
        mRecyclerView.setAdapter(adapter);
    }
}