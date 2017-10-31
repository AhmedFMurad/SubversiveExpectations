package com.a3004.tldr.tldr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



// user log in fragment
public class User_LogIn_Fragment extends AppCompatActivity {
    private Button signin_button;
    private Button signup_button;
    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private FirebaseAuth mFirebaseAuth;
    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initFirebase();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_login);

        signin_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        username = (EditText) findViewById(R.id.user_name_edited);
        emailAddress = (EditText) findViewById(R.id.email_edited);
        password = (EditText) findViewById(R.id.pw_edited);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(User_LogIn_Fragment.this, "YOU CLICKED SIGN IN", Toast.LENGTH_LONG).show();
                userSignin();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(User_LogIn_Fragment.this, "YOU CLICKED SIGN US",Toast.LENGTH_LONG).show();
                userSignup();
            }
        });
    }

   /* @Override
    public void onClick(View v) {
        if(v == signup_button){
            Toast.makeText(this, "YOU CLICKED SIGN UP", Toast.LENGTH_LONG).show();
            userSignup();
        } else if (v == signin_button){
            Toast.makeText(this, "YOU CLICKED SIGN IN", Toast.LENGTH_LONG).show();
            userSignin();
        }
    }*/

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
                                Toast.makeText(User_LogIn_Fragment.this, "User registered!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(User_LogIn_Fragment.this, "An error has occurred, please try again later!", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(User_LogIn_Fragment.this, "Logged in!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(User_LogIn_Fragment.this, "An error has occurred, please try again later!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}