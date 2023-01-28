package com.example.shoppingapp.ui.AccFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.AccInfoAct;
import com.example.shoppingapp.NavActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.ui.HomeFrag.HomeFragment;


public class AccountFragment extends Fragment {
    Activity context;
    private TextView userView,userView2,emailView,emailView2;
    private String user,email;

    public AccountFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = (Activity) getContext();

        getActivity().setTitle("Account Info");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accinfo, container,false);

        userView = view.findViewById(R.id.accInfoUser);
        emailView = view.findViewById(R.id.accInfoEmail);

        userView2 = view.findViewById(R.id.textView5);
        emailView2 = view.findViewById(R.id.textView6);

        Bundle data = getArguments();
        if(data != null){
            user = data.getString("User");
            email = data.getString("Email");

            userView.setText(user);
            emailView.setText(email);

            user = "@ "+user;
            userView2.setText(user);
            emailView2.setText(email);

        }
        else{
            Toast.makeText(context, "BunldeDataNull", Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    public void onStart(){
        super.onStart();

        Button homeBtn = (Button) context.findViewById(R.id.backToHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_nav,
                        new HomeFragment()).commit();
            }
        });

    }


}