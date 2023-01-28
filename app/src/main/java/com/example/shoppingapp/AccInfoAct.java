package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccInfoAct extends AppCompatActivity {
//    TextView emailView;
Button backToHome;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        emailView = findViewById(R.id.textView6);
//        String email = getIntent().getExtras().getString("email");
//        emailView.setText(email);

        backToHome = findViewById(R.id.backToHome);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccInfoAct.this,NavActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}