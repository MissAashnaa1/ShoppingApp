package com.example.shoppingapp;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.ui.AccFragment.AccountFragment;
import com.example.shoppingapp.ui.cartFragment.CartFragment;
import com.example.shoppingapp.ui.HomeFrag.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView emailView;
    private TextView userView;
    MyDBHelper myDBHelper;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        myDBHelper = new MyDBHelper(this);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
//        TextView navUsername = (TextView) headerView.findViewById(R.id.navUsername);
//        navUsername.setText("Your Text Here");

        emailView = headerView.findViewById(R.id.navHeaderEmail);
        emailView.setText(getIntent().getStringExtra("Email"));

        userView = headerView.findViewById(R.id.navHeaderUserName);
        userView.setText(myDBHelper.getUserName1(emailView.getText().toString()));

        String email = getIntent().getStringExtra("Email");
        String user = myDBHelper.getUserName1(emailView.getText().toString());

        data = new Bundle();
        data.putString("Email",email);
        data.putString("User",user);

        /*
        S
        Cursor cursor =

        if (cursor.moveToFirst())
        {
            do
            {
                String path=cursor.getString(0);
                userView.setText(path);
            } while (cursor.moveToNext());
        }*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // try cmntng below listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getIntent().getExtras().getString("email");
                emailView.setText(email);
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_nav,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//         setting email in nav header.

//        emailView = findViewById(R.id.navHeaderEmail);
//        String email = getIntent().getExtras().getString("email");
//        emailView.setText(email);

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(data);

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_nav,
                        homeFragment).commit();
//                startActivity(new Intent(NavActivity.this, HomeAct.class));
                break;
            case R.id.nav_AccInfo:
                AccountFragment accountFragment = new AccountFragment();
                accountFragment.setArguments(data);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_nav,
                        accountFragment).commit();
//                Intent intent = new Intent(NavActivity.this, AccInfoAct.class);
//                intent.putExtra("email",email);
//                startActivity(intent);
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_nav,
                        new CartFragment()).commit();
                break;

            case R.id.signOutText:
                Toast.makeText(this, "logout_success", Toast.LENGTH_SHORT).show();

                myDBHelper.deleteTableCart();
                startActivity(new Intent(NavActivity.this, login.class));
                finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(NavActivity.this, login.class);
            startActivity(intent);
            finish();

//            new AlertDialog.Builder(this)
//                    .setMessage("Are you sure you want to exit?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
        }
    }
}