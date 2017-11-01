package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


// user log in fragment
public class User_LogIn_Fragment extends AppCompatActivity implements View.OnClickListener {
    private Button signin_button;
    private Button signup_button;
    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("users");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initFirebase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_login);
        if(mFirebaseAuth.getCurrentUser() == null) {
            Task<AuthResult> task = mFirebaseAuth.signInAnonymously();
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    // add to the database
                }
            });
        } else if(mFirebaseAuth.getCurrentUser() != null) {
            for (UserInfo user : FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
                if (user.getProviderId().equals("password")) {
                    Intent intentIsLoggedIn = new Intent(User_LogIn_Fragment.this, User_AfterLogIn_Fragment.class);
                    startActivity(intentIsLoggedIn);
                }
            }
        }
        signin_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        username = (EditText) findViewById(R.id.user_name_edited);
        emailAddress = (EditText) findViewById(R.id.email_edited);
        password = (EditText) findViewById(R.id.pw_edited);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userSignin();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Intent intent0 = new Intent(User_LogIn_Fragment.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(User_LogIn_Fragment.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(User_LogIn_Fragment.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == signup_button){
            userSignup();
        } else if (v == signin_button){
            userSignin();
        }
    }

    private void userSignup(){
        final String email = emailAddress.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String userN = username.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email!", Toast.LENGTH_LONG).show();
            return;
        } else if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your password!", Toast.LENGTH_LONG).show();
            return;
        } else {
           /* mFirebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(User_LogIn_Fragment.this, "User registered!", Toast.LENGTH_SHORT).show();
                                Intent intentLoggedIn = new Intent(User_LogIn_Fragment.this, User_AfterLogIn_Fragment.class);
                                startActivity(intentLoggedIn);
                            } else {
                                Toast.makeText(User_LogIn_Fragment.this, "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
            AuthCredential creds = EmailAuthProvider.getCredential(email, pass);
            mFirebaseAuth.getCurrentUser().linkWithCredential(creds).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(User_LogIn_Fragment.this, "User registered!", Toast.LENGTH_SHORT).show();
                        mFirebaseAuth.signInWithEmailAndPassword(email, pass);
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                        mDatabaseReference.child(user.getUid()).child("username").setValue(userN);
                        Intent intentLoggedIn = new Intent(User_LogIn_Fragment.this, User_AfterLogIn_Fragment.class);
                        startActivity(intentLoggedIn);
                    } else {
                        Toast.makeText(User_LogIn_Fragment.this, "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(User_LogIn_Fragment.this, "Logged in!", Toast.LENGTH_SHORT).show();
                                Intent intentLoggedIn = new Intent(User_LogIn_Fragment.this, User_AfterLogIn_Fragment.class);
                                startActivity(intentLoggedIn);
                            } else {
                                Toast.makeText(User_LogIn_Fragment.this, "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}