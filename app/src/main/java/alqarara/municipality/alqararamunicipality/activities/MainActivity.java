package alqarara.municipality.alqararamunicipality.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import alqarara.municipality.alqararamunicipality.R;
import alqarara.municipality.alqararamunicipality.fragments.AdvertisingFragment;
import alqarara.municipality.alqararamunicipality.fragments.HomeFragment;
import alqarara.municipality.alqararamunicipality.fragments.MapFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView tv_title;
    BottomNavigationView bottom_nav;
    ActionBar actionBar;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_title=findViewById(R.id.tv_bar);
        bottom_nav=findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_menu);
        configureToolbar(toolbar);
        Assign();
        loadFragment(new HomeFragment());
        bottom_nav.setSelectedItemId(R.id.menu_bottom_home);
        tv_title.setText("الصفحة الرئيسية");
        configureToolbar(toolbar);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void Assign() {
        actionBar = getSupportActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottom_nav.setOnNavigationItemSelectedListener(MainActivity.this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.drawer_home:
                        tv_title.setText("الصفحة الرئيسية");
                        bottom_nav.setSelectedItemId(R.id.menu_bottom_home);
                        loadFragment(new HomeFragment());
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_sent:
                        Intent intent = new Intent(getApplicationContext(),SentFormActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.drawer_follow:
                        break;
                    case R.id.drawer_logout:
                        break;
                }
                return false;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_bottom_home:
                fragment= new HomeFragment();
                tv_title.setText("الصفحة الرئيسية");
                break;
            case R.id.menu_bottom_advertising:
                tv_title.setText("الإعلانات");
                fragment=new AdvertisingFragment();
                break;
            case R.id.menu_bottom_map:
                fragment=new MapFragment();
                tv_title.setText("الخريطة");
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    public void configureToolbar(Toolbar toolbar) {
        drawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}