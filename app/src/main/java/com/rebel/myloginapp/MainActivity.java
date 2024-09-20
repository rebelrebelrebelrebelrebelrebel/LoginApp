package com.rebel.myloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends MenuMainActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Map<Integer, Class<?>> navigationMap; // Map to associate menu item IDs with activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure your main layout is set here

        // Adjust for system window insets (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set title for the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Login App");
        }

        // Open Register Activity
        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Open Login Activity
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Initialize the drawer layout and navigation view
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Configure the ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Show the navigation button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize navigation map
        initializeNavigationMap();

        // Handle navigation item selections
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId(); // Get the ID of the selected item

            Class<?> activityClass = navigationMap.get(id); // Get the corresponding activity
            if (activityClass != null) {
                startActivity(new Intent(MainActivity.this, activityClass));
            }

            drawerLayout.closeDrawers(); // Close the menu after selection
            return true;
        });
    }

    private void initializeNavigationMap() {
        navigationMap = new HashMap<>();
        navigationMap.put(R.id.nav_home, HomeActivity.class);
        navigationMap.put(R.id.nav_profile, ProfileActivity.class);
        navigationMap.put(R.id.nav_history, HistoryActivity.class);
        navigationMap.put(R.id.nav_logout, null); // Handle logout in another way
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle click on the navigation button
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
