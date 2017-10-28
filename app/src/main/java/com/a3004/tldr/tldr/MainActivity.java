package com.a3004.tldr.tldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    //private SectionsPageAdapter mSectionsPageAdapter;
    //private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_explore:
                        Intent intent1 = new Intent(MainActivity.this, ActivityExplore.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_home:
                        Intent intent2 = new Intent(MainActivity.this, ActivityHome.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_user:
                        Intent intent3 = new Intent(MainActivity.this, ActivityUser.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}