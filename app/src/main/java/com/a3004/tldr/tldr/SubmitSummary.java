package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

public class SubmitSummary extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String url;
    Context context = this;
    TextView content;
    ImageView image;
    TextView title;


    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("categories").child("technology");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_submission);
        initFirebase();
        Bundle intent = getIntent().getExtras();
        url = intent.getString("newUrl").replaceAll("\\.","").replaceAll("/","");
        image = (ImageView) findViewById(R.id.image_view);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference = mDatabaseReference.child(url);
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
    }
}
