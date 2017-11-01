package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
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
                userLogout();
                //Toast.makeText(User_AfterLogIn_Fragment.this, "Log out button pressed", Toast.LENGTH_SHORT).show();
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
                        Intent intent0 = new Intent(User_AfterLogIn_Fragment.this, ActivityExplore.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(User_AfterLogIn_Fragment.this, ActivityHome.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_user:
                        Intent intent2 = new Intent(User_AfterLogIn_Fragment.this, ActivityUser.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    public void userLogout(){
        mFirebaseAuth.signOut();
        Toast.makeText(User_AfterLogIn_Fragment.this, "Logged out!", Toast.LENGTH_SHORT).show();
        Intent intentLoggedIn = new Intent(User_AfterLogIn_Fragment.this, ActivityUser.class);
        startActivity(intentLoggedIn);
    }
}
