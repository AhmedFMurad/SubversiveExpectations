package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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



    public void initFirebase(){
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
        if(mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mDatabaseReference = mFirebaseDatabase.getReference("users");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    Map<String,Boolean> cats = new HashMap<>();
                    cats.put("world", false);
                    cats.put("business", false);
                    cats.put("technology", false);
                    Users tldrUser = new Users("", user.getUid(),cats , 0, 10, 0);
                    mDatabaseReference.child(user.getUid()).setValue(tldrUser);
                }
            });
        }

        /*articlesDifferent = new ArrayList<>();
        ArticleDifferent article1 = new ArticleDifferent("google.com", "Carleton and OttawaU are merging", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        ArticleDifferent article2 = new ArticleDifferent("carleton.ca", "All students get A+", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        ArticleDifferent article3 = new ArticleDifferent("carleton.ca", "Subversive Expectations is the best", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        ArticleDifferent article4 = new ArticleDifferent("test.lol", "Random news about Tesla failing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        ArticleDifferent article5 = new ArticleDifferent("test.lol", "What did Trump say on Twitter today?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");

        articlesDifferent.add(article1);
        articlesDifferent.add(article2);
        articlesDifferent.add(article3);
        articlesDifferent.add(article4);
        articlesDifferent.add(article5);

        listview = (ListView) findViewById(R.id.cardList);

        cardAdapter = new CardViewActivity(ActivityHome.this, articlesDifferent);
        listview.setAdapter(cardAdapter);*/

        /*loadfeed loads the feed*/
        /*can change it to show whenever the categories are selected to be true*/
        //loadFeed(url1);
        //loadFeed(url2);
        loadFeed(url3);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
// comment
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
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

    public void loadFeed(String url){
        Parser parser = new Parser();
        parser.execute(url);
        parser.onFinish(new Parser.OnTaskCompleted(){
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                listview = (ListView) findViewById(R.id.cardList);
                mDatabaseReference = mFirebaseDatabase.getReference("categories");
                mDatabaseReference.setValue(mCategory.getTitle());

                for (int i = 0; i < list.size(); i++){
                    String url = list.get(i).getLink().replaceAll("\\.","");
                    url = url.replaceAll("/","");
                    Toast.makeText(getApplicationContext(), list.get(i).getLink(), Toast.LENGTH_SHORT).show();
                    //allArticles.add(list.get(i));
                    //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                    mDatabaseReference.child(mCategory.getTitle()).child(url).setValue(list.get(i));

                }
                Toast.makeText(getApplicationContext(), "THE LOOP ENDED", Toast.LENGTH_SHORT).show();
                //articleAdapter = new ArticleAdapter(allArticles, ActivityHome.this);
                //listview.setAdapter(articleAdapter);

            }

            @Override
            public void onError() {

            }
        });
    }
}
