package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    private EditText usernameView,passwordView;
    private String username;
    private Button deletebtn;
    private Button gotoRegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        usernameView =findViewById(R.id.editusername);
        passwordView = findViewById(R.id.editpassword);
        deletebtn = findViewById(R.id.removeUser);
        gotoRegBtn = findViewById(R.id.gotoReg);

        MyDBHelper myDBHelper = new MyDBHelper(this);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(EditUser.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = myDBHelper.checkUsernamePassword(username, password);
                    if (checkUser == true) {
                        myDBHelper.deleteUser(username);
                        clearFields();
                        Toast.makeText(EditUser.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        clearFields();
                        Toast.makeText(EditUser.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        gotoRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void clearFields(){
        usernameView.getText().clear();
        passwordView.getText().clear();
    }
}