package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewSummaries extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String url;
    Context context = this;
    TextView content;
    ImageView image;
    TextView title;
    ImageView addSum;
    ArrayList<Summary> mSummaries = new ArrayList<>();
    private ViewSummaryAdapter summaryAdapter;
    private ListView listview;

    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_view);
        initFirebase();
        Bundle intent = getIntent().getExtras();
        url = intent.getString("newUrl").replaceAll("\\.","").replaceAll("/","");
        image = (ImageView) findViewById(R.id.image_view);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        addSum = (ImageView) findViewById(R.id.summary_button);

        addSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubmitSummary.class);
                intent.putExtra("newUrl", url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listview = (ListView) findViewById(R.id.summaryList);
        mDatabaseReference = mDatabaseReference.child("categories").child("technology").child(url);
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Article article = new Article();
                article.setAuthor((String) dataSnapshot.child("author").getValue());
                article.setContent((String) dataSnapshot.child("content").getValue());
                article.setDescription((String) dataSnapshot.child("description").getValue());
                article.setImage((String) dataSnapshot.child("image").getValue());
                article.setTitle((String) dataSnapshot.child("title").getValue());
                article.setLink((String) dataSnapshot.child("link").getValue());
                Picasso.with(context).load(article.getImage()).into(image);
                title.setText(article.getTitle());
                content.setText(article.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference = mDatabaseReference.child("summaries").child(url);
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Summary summary = new Summary();
                    summary.setContent((String) postSnapshot.child("summary").child("content").getValue());
                    summary.setUID((String) postSnapshot.child("summary").child("uid").getValue());
                    summary.setUrl(url);
                    mSummaries.add(summary);
                }
                summaryAdapter = new ViewSummaryAdapter(mSummaries, ViewSummaries.this);
                listview.setAdapter(summaryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, ActivityHome.class);
        context.startActivity(intent);
    }
}
