package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityHome extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private CardViewActivity cardAdapter;
    private ListView listview;
    private ArrayList<Article> articles;
    private final String url = "http://rss.nytimes.com/services/xml/rss/nyt/Education.xml";


    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("users");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFirebase();
        if(mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
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

        articles = new ArrayList<>();
        Article article1 = new Article("google.com", "Carleton and OttawaU are merging", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        Article article2 = new Article("carleton.ca", "All students get A+", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        Article article3 = new Article("carleton.ca", "Subversive Expectations is the best", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        Article article4 = new Article("test.lol", "Random news about Tesla failing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");
        Article article5 = new Article("test.lol", "What did Trump say on Twitter today?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam feugiat justo eget ligula suscipit dapibus. Duis mollis pulvinar finibus. Mauris feugiat semper porttitor. Cras convallis, diam a maximus convallis, nibh enim tincidunt libero, eu congue est odio id nulla. Nunc sagittis quam et vestibulum efficitur. Suspendisse commodo, lacus at fringilla consectetur, dolor risus laoreet orci, at fermentum sapien tellus et ligula.");

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);

        listview = (ListView) findViewById(R.id.cardList);

        cardAdapter = new CardViewActivity(ActivityHome.this, articles);
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
}
