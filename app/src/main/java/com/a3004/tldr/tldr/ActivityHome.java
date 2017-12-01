package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;


public class ActivityHome extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private CardViewActivity cardAdapter;
    private ArticleAdapter articleAdapter;
    private ListView listview;
    private ArrayList<ArticleDifferent> articlesDifferent;
    private ArrayList<Article> allArticles = new ArrayList<>();
    private final String url1 = "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";
    private final String url2 = "http://rss.cbc.ca/lineup/canada.xml";
    private final String url3 = "https://www.androidauthority.com/feed/";
    private Category mCategory;
    Context context = this;
    Article article;
    public boolean yes;

    public void initFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFirebase();
        mCategory = new Category();
        mCategory.setTitle("technology");
        if (mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mDatabaseReference = mFirebaseDatabase.getReference("users");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    Map<String, Boolean> cats = new HashMap<>();
                    cats.put("world", false);
                    cats.put("business", false);
                    cats.put("technology", false);
                    Users tldrUser = new Users("", user.getUid(), cats, 0, 10, 0);
                    mDatabaseReference.child(user.getUid()).setValue(tldrUser);
                }
            });
        }
        loadFeed(url3);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_explore:
                        Intent intent0 = new Intent(ActivityHome.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        // Intent intent1 = new Intent(ActivityHome.this, ActivityHome.class);
                        // startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(ActivityHome.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    public void loadFeed(String url) {
        Parser parser = new Parser();
        parser.execute(url);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {

                mDatabaseReference = mFirebaseDatabase.getReference("categories");

                for (int i = 0; i < list.size(); i++) {
                    String url = list.get(i).getLink().replaceAll("\\.", "");
                    url = url.replaceAll("/", "");

                    mDatabaseReference.child(mCategory.getTitle()).child(url).setValue(list.get(i));
                    if (list.get(i).getImage() == "") {
                        mDatabaseReference.child(mCategory.getTitle()).child(url).child("image").removeValue();
                    }

                }

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listview = (ListView) findViewById(R.id.cardList);
        mDatabaseReference = mFirebaseDatabase.getReference();

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                yes = false;
                if (dataSnapshot
                        .child("users")
                        .child(mFirebaseAuth.getCurrentUser().getUid())
                        .child("preferredCategories")
                        .child("technology").getValue().toString() == "true") {
                    for (DataSnapshot postSnapshot : dataSnapshot.child("categories").child("technology").getChildren()) {
                        //Article article = new Article();
                        article = new Article();
                        article.setAuthor((String) postSnapshot.child("author").getValue());
                        article.setContent((String) postSnapshot.child("content").getValue());
                        article.setDescription((String) postSnapshot.child("description").getValue());
                        article.setImage((String) postSnapshot.child("image").getValue());
                        article.setTitle((String) postSnapshot.child("title").getValue());
                        article.setLink((String) postSnapshot.child("link").getValue());
                        allArticles.add(article);


                    }
                } else if (dataSnapshot.child("users")
                        .child(mFirebaseAuth.getCurrentUser().getUid())
                        .child("preferredCategories")
                        .child("business").getValue().toString() == "true") {
                    for (DataSnapshot postSnapshot : dataSnapshot.child("categories").child("business").getChildren()) {

                        //Article article = new Article();
                        article = new Article();
                        article.setAuthor((String) postSnapshot.child("author").getValue());
                        article.setContent((String) postSnapshot.child("content").getValue());
                        article.setDescription((String) postSnapshot.child("description").getValue());
                        article.setImage((String) postSnapshot.child("image").getValue());
                        article.setTitle((String) postSnapshot.child("title").getValue());
                        article.setLink((String) postSnapshot.child("link").getValue());
                        allArticles.add(article);
                    }
                } else if (dataSnapshot.child("users")
                        .child(mFirebaseAuth.getCurrentUser().getUid())
                        .child("preferredCategories")
                        .child("world").getValue().toString() == "true") {

                    for (DataSnapshot postSnapshot : dataSnapshot.child("categories").child("world").getChildren()) {
                        //Article article = new Article();
                        article = new Article();
                        article.setAuthor((String) postSnapshot.child("author").getValue());
                        article.setContent((String) postSnapshot.child("content").getValue());
                        article.setDescription((String) postSnapshot.child("description").getValue());
                        article.setImage((String) postSnapshot.child("image").getValue());
                        article.setTitle((String) postSnapshot.child("title").getValue());
                        article.setLink((String) postSnapshot.child("link").getValue());
                        allArticles.add(article);
                    }
                }

                articleAdapter = new ArticleAdapter(allArticles, ActivityHome.this);

                listview.setAdapter(articleAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void setDescription(String s) {

    }

}
