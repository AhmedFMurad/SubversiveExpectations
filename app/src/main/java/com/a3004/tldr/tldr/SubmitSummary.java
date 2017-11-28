package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubmitSummary extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private String url;
    Context context = this;
    TextView content;
    ImageView image;
    TextView title;
    TextInputEditText submission;
    Button submit;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        //.child("categories").child("technology");
        mFirebaseAuth = FirebaseAuth.getInstance();

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
        submission = (TextInputEditText) findViewById(R.id.summary_text2);
        submit = (Button) findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Summary summary = new Summary();
                ArrayList<String> empty = new ArrayList<String>();
                empty.add(null);
                summary.setDownvotes(empty);
                summary.setUpvotes(empty);
                summary.setUID(mFirebaseAuth.getCurrentUser().getUid());
                summary.setContent(submission.getText().toString().trim());
                summary.setUrl(url);
                mDatabaseReference = mFirebaseDatabase.getReference();
                mDatabaseReference.child("summaries")
                        .child(url)
                        .child(mFirebaseAuth.getCurrentUser().getUid())
                        .child("summary")
                        .setValue(summary);
                Intent intent = new Intent(context, ViewSummaries.class);
                intent.putExtra("newUrl", url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
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
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, ViewSummaries.class);
        intent.putExtra("newUrl", url);
        context.startActivity(intent);
    }
}
