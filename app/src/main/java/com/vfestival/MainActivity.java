package com.vfestival;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vfestival.Fragments.AccountFragment;
import com.vfestival.Fragments.InfoFragment;
import com.vfestival.Fragments.LineUpFragment;
import com.vfestival.Fragments.PleaseLoginFragment;
import com.vfestival.Fragments.RegisterFragment;
import com.vfestival.Fragments.TicketsFragment;
import com.vfestival.Fragments.LoginFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;
    private static boolean userIsLoggedIn = false;
    private FirebaseAuth mAuth;
    private static View header;
    private static NavigationView navigationView;
    private FirebaseAuth.AuthStateListener authListener;

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_view, fragment);
        ft.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    fragment = new InfoFragment();
                    switchToFragment(fragment);
                    return true;
                case R.id.navigation_lineup:
                    fragment = new LineUpFragment();
                    switchToFragment(fragment);
                    return true;
                case R.id.navigation_tickets:
                    if (userIsLoggedIn == true){
                        fragment = new TicketsFragment();
                        switchToFragment(fragment);
                    }else if (userIsLoggedIn == false) {
                        fragment = new PleaseLoginFragment();
                        switchToFragment(fragment);
                    }
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (user == null) {
                    updateUI(user);
                    finish();
                    userIsLoggedIn = false;
                } else {
                    updateUI(user);
                    userIsLoggedIn = true;
                }
            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment = new LineUpFragment();

        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_view, fragment);
            ft.commit();
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_lineup);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.getHeaderView(0);

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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.nav_login:
                fragment = new LoginFragment();
                switchToFragment(fragment);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_register:
                fragment = new RegisterFragment();
                switchToFragment(fragment);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_account:
                fragment = new AccountFragment();
                switchToFragment(fragment);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_signout:
                signOut();
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }
    public static void updateUI(FirebaseUser user) {
        if (user != null) {
            String email = user.getEmail();
            TextView emaillabel = (TextView) header.findViewById(R.id.emailLabel);
            TextView welcomeLabel = (TextView) header.findViewById(R.id.welcomeLabel);
            welcomeLabel.setText("Hi,");
            emaillabel.setText(email);
            navigationView.getMenu().setGroupVisible(R.id.logged_in, true);
            navigationView.getMenu().setGroupVisible(R.id.logged_out, false);
            userIsLoggedIn = true;
        } else {
            TextView emaillabel = (TextView) header.findViewById(R.id.emailLabel);
            TextView welcomeLabel = (TextView) header.findViewById(R.id.welcomeLabel);
            welcomeLabel.setText("Hi, User");
            emaillabel.setText("Please create an account to sign in");
            navigationView.getMenu().setGroupVisible(R.id.logged_in, false);
            navigationView.getMenu().setGroupVisible(R.id.logged_out, true);
            userIsLoggedIn = false;
        }
    }
    public void signOut() {
        mAuth.signOut();
        updateUI(null);
        Toast.makeText(getApplicationContext(), "You have been signed out", Toast.LENGTH_SHORT).show();
    }
}
