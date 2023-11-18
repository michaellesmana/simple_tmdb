package com.example.moviedb4;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MovieFragment())
                .commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                Log.d("VCustom Apakah trigger di sini : ", "part navigation");

                int itemId = item.getItemId();

                Log.d("VCustom item id : ", String.valueOf(itemId));

                if (itemId == R.id.nav_movies_show_popular) {
                    SharedVariables.MENU = 0;

                    ((MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).fetchPopularMovies(true);
                } else if (itemId == R.id.nav_movies_show_now_showing) {
                    SharedVariables.MENU = 1;

                    ((MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).fetchNowPlayingMovies(true);
                } else if (itemId == R.id.nav_movies_show_upcoming) {
                    SharedVariables.MENU = 2;

                    ((MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).fetchUpcomingMovies(true);
                } else if (itemId == R.id.nav_movies_filter_genre) {
                    SharedVariables.MENU = 3;

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new GenreFragment()) // Replace with your desired fragment
                            .addToBackStack(null)
                            .commit();
                } else if (itemId == R.id.nav_tv_show_popular) {
                    SharedVariables.MENU = 4;

                    ((MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).fetchPopularTVs(true);
                } else if (itemId == R.id.nav_tv_filter_genre) {
                    SharedVariables.MENU = 5;
                } else if (itemId == R.id.nav_people_popular) {
                    SharedVariables.MENU = 6;

                    ((MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).fetchPopularPerson(true);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.d("VCustom Apakah di trigger di sini : ", "Part actionBar");

            Log.d("VCustom item ID : ", String.valueOf(item.getItemId()));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}