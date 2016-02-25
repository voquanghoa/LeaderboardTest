package com.lovelybroteam.quanghoatestleaderboard;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends BaseGameActivity
implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private LoginLogOutFragment loginLogOutFragment;
    private LeaderboardActivity leaderboardFragment1;
    private LeaderboardActivity leaderboardFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        loginLogOutFragment = new LoginLogOutFragment();
        leaderboardFragment1 = createLeaderBoardFragment(R.string.leaderboard_test_score_1_name, R.string.leaderboard_test_score_1);
        leaderboardFragment2 = createLeaderBoardFragment(R.string.leaderboard_test_score_2_name, R.string.leaderboard_test_score_2);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, loginLogOutFragment)
                .add(R.id.fragment_container, leaderboardFragment1)
                .add(R.id.fragment_container, leaderboardFragment2)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public LeaderboardActivity createLeaderBoardFragment(int stringNameId, int stringIdId){
        LeaderboardActivity leaderboardFragment = new LeaderboardActivity();
        leaderboardFragment.setLeaderBoardId(getString(stringIdId));
        leaderboardFragment.setLeaderBoardName(getString(stringNameId));
        return leaderboardFragment;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_camera){
            setFragmentView(loginLogOutFragment);
        }else if (id == R.id.nav_gallery) {
            setFragmentView(leaderboardFragment1);
        } else if (id == R.id.nav_slideshow) {
            setFragmentView(leaderboardFragment2);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragmentView(Fragment view){
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.remove(view);
        transaction.replace(R.id.fragment_container, view);
        transaction.addToBackStack(null);
        transaction.commit();
        this.setTitle(view.toString());
    }

    public void onSignInSucceeded() {
        Toast.makeText(this,"onSignInSucceeded",Toast.LENGTH_SHORT).show();
    }

    public void onSignInFailed() {
        Toast.makeText(this, "onSignInFailed", Toast.LENGTH_SHORT).show();
    }
}
