package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /*private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    public void initFirebase(String table){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference(table);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Map<String,String> rss = new HashMap<>();
        Website newWeb = new Website("cnn", rss);
        newWeb.addFeed("Top Stories", "http://rss.cnn.com/rss/cnn_topstories.rss");
        newWeb.addFeed("World", "http://rss.cnn.com/rss/cnn_world.rss");
        initFirebase("Websites");
        mDatabaseReference.child(newWeb.getSiteTitle()).setValue(newWeb.getFeeds());*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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