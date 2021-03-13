package com.example.ecommermattevan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommermattevan.ui.gallery.GalleryFragment;
import com.example.ecommermattevan.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavigationViewInit extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    String valorEmail;
    String valorName;
    String valorPhoto;
    TextView tv_email;
    TextView tv_name;
    ImageView img_photo;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_init);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        valorEmail = getIntent().getExtras().getString("email");
        valorName = getIntent().getExtras().getString("name");

        SharedPreferences prefs = getSharedPreferences("MattEvan", MODE_PRIVATE);
       valorPhoto = prefs.getString("photo", "");//"No name defined" is the default value.

        navigationView = findViewById(R.id.nav_view);


        Toast.makeText(NavigationViewInit.this,
                "Bienvenido: "+valorEmail, Toast.LENGTH_LONG).show();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tv_email= headerView.findViewById(R.id.tv_email);
        tv_name= headerView.findViewById(R.id.tv_name);
        img_photo= headerView.findViewById(R.id.img_photo);
        tv_email.setText(valorEmail);
        tv_name.setText(valorName);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_background);
        HomeFragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit();

        Glide.with(this).load(valorPhoto)
                .apply(requestOptions).thumbnail(0.5f).into(img_photo);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_exit:
                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                build();

                        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(NavigationViewInit.this,gso);
                        googleSignInClient.signOut();

                        Intent intent= new Intent(NavigationViewInit.this,MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_main:
                        HomeFragment fragment = new HomeFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();

                        fragmentManager.beginTransaction()
                                .replace(R.id.frame, fragment)
                                .addToBackStack(null)
                                .commit();

                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.nav_pedidos:
                        GalleryFragment fragments = new GalleryFragment();
                        FragmentManager fragmentManagers = getSupportFragmentManager();
                        fragmentManagers
                                .beginTransaction()
                                .replace(R.id.frame, fragments)
                                .addToBackStack(null)

                                .commit();

                        drawerLayout.closeDrawers();


                    return true;




                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_view_init, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}