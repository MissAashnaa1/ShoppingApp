package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.ui.HomeFrag.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private EditText usernameView;
    private EditText emailView;
    private EditText passwordView;
    private EditText passwordConfView;
    private Button regbtn;
    private Button gotoEdit;

    private String username, email, password, passwordConf;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper myDBHelper = new MyDBHelper(this);

        regbtn = findViewById(R.id.button3);
        usernameView = findViewById(R.id.editTextTextPersonName2);
        emailView = findViewById(R.id.editTextTextEmailAddress);
        passwordView = findViewById(R.id.editTextTextPassword);
        passwordConfView = findViewById(R.id.editTextTextPassword2);
        gotoEdit = findViewById(R.id.gotoEditbtn);

//        regbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // extracting text from input fields
//
//                if(validateEmail(emailView)){
//                    email = emailView.getText().toString();
//                    username = usernameView.getText().toString();
//                    password = passwordView.getText().toString();
//                    passwordConf = passwordConfView.getText().toString();
//
//                    if(isSamePass(password,passwordConf)){
//                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//
//                        // inserting data
//                        myDBHelper.addCreds(username, email, password);
//
//                        // text clear
//                        clearAll();
//                    }else{
//                        clearPassFields();
//                    }
//                }else{
//                    emailView.getText().clear();
//                }
//            }
//        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // extracting text from input fields
                email = emailView.getText().toString();
                username = usernameView.getText().toString();
                password = passwordView.getText().toString();
                passwordConf = passwordConfView.getText().toString();

                if(email.equals("") || username.equals("") || password.equals("") || passwordConf.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all fields!", Toast.LENGTH_SHORT).show();
                }
               else if (validateEmail(emailView)) {
                    if (isSamePass(password, passwordConf) ) {
                        Boolean checkuser = myDBHelper.checkUsername(username);
                        Boolean checkemail = myDBHelper.checkEmail(email);

                        if(checkuser == false && checkemail == false){
                            // inserting data
                            boolean insert = myDBHelper.addCreds(username, email, password);
                            if(insert == true) {
                                // text clear
                                clearAll();
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            clearAll();
                            Toast.makeText(MainActivity.this, "User aleady exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        clearPassFields();
                    }
//                  Log.d("helpp", username);
                } else {
                    emailView.getText().clear();
                }
            }
        });

        TextView textView = findViewById(R.id.textViewL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        });


        // skip login btn
//        Button btnTest = findViewById(R.id.buttonTesting);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NavActivity.class);
//                intent.putExtra("User","bunty");
//                intent.putExtra("Email","buntyy0@gmail.com");
//                startActivity(intent);
//                finish();
//            }
//        });

        gotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditUser.class);
                startActivity(intent);
                finish();
            }
        });

//        myDBHelper.addCreds("Bunty0", "buntyy0@gmail.com","00000");
//        myDBHelper.addCreds("AAusername", "aa@gmail.com","aa");
    }

    private void clearAll(){
        usernameView.getText().clear();
        emailView.getText().clear();
        passwordView.getText().clear();
        passwordConfView.getText().clear();
    }

    private void clearPassFields(){
        passwordView.getText().clear();
        passwordConfView.getText().clear();
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

    private boolean isSamePass(String pass, String passCnf){
        if(pass.isEmpty() || passCnf.isEmpty()){
            Toast.makeText(this, "Field cant be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.equals(passCnf)){
            return true;
        }
        else{
            Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}