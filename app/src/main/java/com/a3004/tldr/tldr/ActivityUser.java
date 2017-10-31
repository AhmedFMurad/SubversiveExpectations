package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class ActivityUser extends AppCompatActivity{
    private Button signin_button;
    private Button signup_button;
    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private FirebaseAuth mFirebaseAuth;
    static final String TAG = "ERROR LOG";
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_login);
        initFirebase();
        signin_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        username = (EditText) findViewById(R.id.user_name_edited);
        emailAddress = (EditText) findViewById(R.id.email_edited);
        password = (EditText) findViewById(R.id.pw_edited);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ActivityUser.this, "YOU CLICKED SIGN IN", Toast.LENGTH_LONG).show();
               userSignin();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(ActivityUser.this, "YOU CLICKED SIGN US",Toast.LENGTH_LONG).show();
                userSignup();
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_explore:
                        Intent intent0 = new Intent(ActivityUser.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(ActivityUser.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(ActivityUser.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    private void userSignup(){
        String email = emailAddress.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String user = username.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email!", Toast.LENGTH_LONG).show();
            return;
        } else if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your password!", Toast.LENGTH_LONG).show();
            return;
        } else {
            mFirebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ActivityUser.this, "User registered!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ActivityUser.this, "An error has occurred, please try again later!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

    private void userSignin(){
        String email = emailAddress.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String user = username.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email!", Toast.LENGTH_LONG).show();
            return;
        } else if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your password!", Toast.LENGTH_LONG).show();
            return;
        } else {
            mFirebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ActivityUser.this, "Logged in!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ActivityUser.this, "An error has occurred, please try again later!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}