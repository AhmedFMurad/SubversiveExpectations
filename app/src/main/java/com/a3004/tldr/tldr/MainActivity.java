package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private CardViewActivity cardAdapter;
    private ListView listview;
    private ArrayList<Article> articles;
   /* public void initFirebase(String table){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference(table);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent intent0 = new Intent(MainActivity.this, ActivityHome.class);
        //startActivity(intent0);
        //finish();
        /*Map<String,String> rss = new HashMap<>();
        Website newWeb = new Website("cnn", rss);
        newWeb.addFeed("Top Stories", "http://rss.cnn.com/rss/cnn_topstories.rss");
        newWeb.addFeed("World", "http://rss.cnn.com/rss/cnn_world.rss");

        initFirebase("Websites");
        mDatabaseReference.child(newWeb.getSiteTitle()).setValue(newWeb.getFeeds());*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articles = new ArrayList<>();
        Article article1 = new Article("google.com", "Carleton news", "eet orci, at fermentum sapien tellus et ligula.");
        Article article2 = new Article("carleton.ca", "Ottawa news", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        Article article3 = new Article("test.lol", "Tech news", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        listview = (ListView) findViewById(R.id.cardList);

        cardAdapter = new CardViewActivity(MainActivity.this, articles);
        listview.setAdapter(cardAdapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
               switch (item.getItemId()){
                    case R.id.ic_explore:
                        Intent intent0 = new Intent(MainActivity.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(MainActivity.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(MainActivity.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}