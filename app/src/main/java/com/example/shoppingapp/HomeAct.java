package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shoppingapp.ui.HomeFrag.HomeFragment;
import com.example.shoppingapp.ui.cartFragment.CartFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeAct extends AppCompatActivity {
//    ic_baseline_shopping_cart2_24
private ImageView imgView1,imgView2,imgView3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgView1 = findViewById(R.id.imageView9);
        imgView2 = findViewById(R.id.imageView11);
        imgView3 = findViewById(R.id.imageView14);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView1.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
            }
        });

        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView2.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
            }
        });

        imgView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView3.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
            }
        });

    }
}