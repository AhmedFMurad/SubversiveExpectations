package com.a3004.tldr.tldr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


// after user log in fragment
public class User_AfterLogIn_Fragment extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;

    public void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initFirebase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_afterlogin);

        Button logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //log out stuff
                Toast.makeText(User_AfterLogIn_Fragment.this, "Log out button pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
