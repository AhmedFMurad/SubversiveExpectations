package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActivityHome extends AppCompatActivity {

    // --- recycler view part ---

    //private RecyclerView mRecyclerView;
    //private RecyclerViewAdapter adapter;
    //private ArrayList<Article> articles;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("users");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // POPULATING THE DATABASE WITH ARTICLES
        FirebaseApp.initializeApp(this);
        parseXML("http://rss.nytimes.com/services/xml/rss/nyt/World.xml");
        initFirebase();
        if(mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    ArrayList<String> cats = new ArrayList<>();
                    cats.add("");
                    Users tldrUser = new Users("", user.getUid(),cats , 0, 10, 0);
                    mDatabaseReference.child(user.getUid()).setValue(tldrUser);
                }
            });
        }
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
                        Intent intent1 = new Intent(ActivityHome.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(ActivityHome.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initData();
        //initView();
    }
/*
    // --- initial ArrayList data ---
    private void initData() {
        articles = new ArrayList<>();

        // add articles here
        // need to link to a website
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
        articles.add(new Article("", "Breaking News", R.drawable.pepe1));
        articles.add(new Article("", "Breaking News", R.drawable.pepe2));
    }

    // --- initial recycler view ---
    private void initView() {
        // set adapter
        adapter = new RecyclerViewAdapter(ActivityHome.this, articles);
        mRecyclerView.setAdapter(adapter);

        // set layout for recycler view
        LinearLayoutManager manager = new LinearLayoutManager(ActivityHome.this);
        mRecyclerView.setLayoutManager(manager);

        // set item listener for recycler view
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ActivityHome.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
    */
public void parseXML(String site){
    try{
            /* Make connection with the url*/
        URL url = new URL(site);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(url.openStream()));
        Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("item");
        //get titles of articles
        for (int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);

            //get titles
            NodeList title = element.getElementsByTagName("title");
            Element titleLine = (Element) title.item(0);
            String titleString = titleLine.getTextContent();

            //get links
            NodeList link = element.getElementsByTagName("link");
            Element linkLine = (Element) link.item(0);
            String linkString = linkLine.getTextContent();


            //getting descriptions
            NodeList description = element.getElementsByTagName("description");
            Element descLine = (Element) description.item(0);
            String descString = descLine.getTextContent();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference("categories");
            mDatabaseReference.child("articles").setValue("1");
            mDatabaseReference.child("articles").child(linkString).child("title").setValue(titleString);
            mDatabaseReference.child("articles").child(linkString).child("desc").setValue(descString);
            Toast.makeText(this, "article added "+i, Toast.LENGTH_SHORT).show();
        }
    } catch (Exception e){
        e.printStackTrace();
    }

}
}
