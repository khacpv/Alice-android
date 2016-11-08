package com.namestore.alicenote.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.fragment.DashBoardFragment;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;


import static com.namestore.alicenote.data.Constants.NUM_PAGES;

public class MainActivity extends CoreActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Button btnDashBoard, btnCalendar, btnClient, btnService, btnMore;
    private DashBoardFragment mDashBoardFragment =new DashBoardFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPager = (ViewPager) findViewById(R.id.viewPagerMain);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

  /*      btnDashBoard = (Button) findViewById(R.id.btnDashBoard);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnClient = (Button) findViewById(R.id.btnClient);
        btnService = (Button) findViewById(R.id.btnService);
        btnMore = (Button) findViewById(R.id.btnMore);*/





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void showDashBoardFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDashBoardFragment).commit();

    }
    public void showClientFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDashBoardFragment).commit();

    }
    public void showServiceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDashBoardFragment).commit();

    }
    public void showCalendarFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDashBoardFragment).commit();

    }
    public void showMoreFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mDashBoardFragment).commit();

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DashBoardFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnCalendar:
                showCalendarFragment();
            case R.id.btnClient:
                showClientFragment();
            case R.id.btnDashBoard:
                showDashBoardFragment();
            case R.id.btnService:
                showServiceFragment();
            case R.id.btnMore:
                showMoreFragment();


        }

    }





}
