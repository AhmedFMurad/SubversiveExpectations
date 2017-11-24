package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SubmitSummary extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_submission);

        Intent intent = getIntent();

        String url = intent.getStringExtra(ArticleAdapter.ARTICLE_URL);

        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    }
}
