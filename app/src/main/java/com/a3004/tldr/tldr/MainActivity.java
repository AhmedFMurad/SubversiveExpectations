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
        setContentView(R.layout.activity_main);
    }

}