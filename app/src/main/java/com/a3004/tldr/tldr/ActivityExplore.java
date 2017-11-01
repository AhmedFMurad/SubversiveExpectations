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

public class ActivityExplore extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        initFirebase();
        if(mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(ActivityExplore.this, "Anon account done", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    // add to the database
                }
            });
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_explore:
                        Intent intent0 = new Intent(ActivityExplore.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(ActivityExplore.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(ActivityExplore.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
