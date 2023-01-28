package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.view.GravityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.ui.HomeFrag.HomeFragment;

public class login extends AppCompatActivity {

    EditText emailView, passwordView;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailView = findViewById(R.id.editTextTextEmailAddress);
        passwordView = findViewById(R.id.editTextTextPassword);

        myDBHelper = new MyDBHelper(this);
        MyDBHelper myDBHelper2 = new MyDBHelper(this);

        TextView textView = findViewById(R.id.newuser);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnLogin = findViewById(R.id.loginbtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(login.this, "Enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else if(validateEmail(emailView)){
                    Boolean checkUser = myDBHelper.checkemailpassword(email,password);
                    if( checkUser == true){

                        Toast.makeText(login.this, "Success", Toast.LENGTH_SHORT).show();
                        clearFields();

                        Intent intent = new Intent(login.this, NavActivity.class);


                        intent.putExtra("Email",email);

//                        Toast.makeText(login.this, email, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(login.this, user, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(login.this,"Invalid Credentials! ", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                }
                else{
                    clearFields();
                }
            }
        });
    }

    private boolean validateEmail(EditText email){
        String emailInput = email.getText().toString();
        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
//            Toast.makeText(this, "Valid email", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void clearFields(){
        emailView.getText().clear();
        passwordView.getText().clear();
    }

//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setMessage("Are you sure you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }
}