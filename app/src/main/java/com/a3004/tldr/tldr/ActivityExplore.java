package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
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
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class ActivityExplore extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser user;
    Switch switchTech;
    Switch switchBusiness;
    Switch switchWorld;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = mFirebaseDatabase.getReference("users").child(user.getUid());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        initFirebase();
        switchWorld = (Switch) findViewById(R.id.cat1_switch);
        switchTech = (Switch) findViewById(R.id.cat2_switch);
        switchBusiness = (Switch) findViewById(R.id.cat3_switch);
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
                       // Intent intent0 = new Intent(ActivityExplore.this, ActivityExplore.class);
                       // startActivity(intent0);
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

        switchWorld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mDatabaseReference.child("preferredCategories").child("world").setValue(true);
                } else {
                    mDatabaseReference.child("preferredCategories").child("world").setValue(false);
                }
            }
        });

        switchTech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDatabaseReference.child("preferredCategories").child("technology").setValue(true);
                } else {
                    mDatabaseReference.child("preferredCategories").child("technology").setValue(false);
                }
            }
        });

        switchBusiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDatabaseReference.child("preferredCategories").child("business").setValue(true);
                } else {
                    mDatabaseReference.child("preferredCategories").child("business").setValue(false);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                switchBusiness.setChecked((Boolean) dataSnapshot.child("preferedCategories").child("business").getValue());
                switchTech.setChecked((Boolean) dataSnapshot.child("preferedCategories").child("technology").getValue());
                switchWorld.setChecked((Boolean) dataSnapshot.child("preferedCategories").child("world").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
