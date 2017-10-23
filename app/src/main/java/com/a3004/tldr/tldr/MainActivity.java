package com.a3004.tldr.tldr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    // --- recycler view part ---
    private RecyclerView mRecyclerView;
    private ArrayList<Article> articles;

    // --- menu part ---
    //private DrawerLayout mDrawerLayout;
    //private ViewPager mViewPager;
    //private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        // added stuff from here

        // --- recycler view part ---

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // --- menu part ---
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            return true;
                        }
                    });
        }

        initViewPager();
        */

        /*  not a necessary part
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        */

        initData();
        initAdapter();

    }

    // --- recycler view part ---

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

    // --- recycler view part ---
    private void initAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(articles);
        mRecyclerView.setAdapter(adapter);
    }


    // --- menu part ---
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    // --- menu part ---
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --- menu part ---
    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("category1");
        titles.add("category2");
        titles.add("category3");

        for(int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }

        List<Fragment> fragments = new ArrayList<>();
        for(int i = 0; i < titles.size(); i++) {
            fragments.add(new ListFragment());
        }

        FragmentAdapter mFragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }
    */
}