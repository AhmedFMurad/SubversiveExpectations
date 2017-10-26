package com.a3004.tldr.tldr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //private SectionsPageAdapter mSectionsPageAdapter;
    //private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_afterlogin);

        //mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mViewPager = (ViewPager) findViewById(R.id.container);
        //setupViewPager(mViewPager);
/*
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_explore_white_18dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home_white_18dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_face_white_18dp);

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

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabExploreFragment());
        adapter.addFragment(new TabHomeFragment());
        adapter.addFragment(new TabUserFragment());
        viewPager.setAdapter(adapter);
    }
    */
    }
}