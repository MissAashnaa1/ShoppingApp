package com.example.shoppingapp.ui.HomeFrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.shoppingapp.MyDBHelper;
import com.example.shoppingapp.R;


public class HomeFragment extends Fragment {
    private ImageView imgView1,imgView2,imgView3;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
        // Inflate the layout for this fragment
        MyDBHelper myDBHelper = new MyDBHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_home, container,false);

        getActivity().setTitle("Home");

        imgView1 = view.findViewById(R.id.imageView91);
        imgView2 = view.findViewById(R.id.imageView111);
        imgView3 = view.findViewById(R.id.imageView141);

        if(myDBHelper.ifExists("outfit1")) {
            imgView1.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
        }
        if(myDBHelper.ifExists("outfit2")) {
            imgView2.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
        }
        if(myDBHelper.ifExists("outfit3")) {
            imgView3.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
        }

        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!myDBHelper.ifExists("outfit1")) {
                    imgView1.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
                    myDBHelper.addIntoCart("outfit1", "1" , "1600" , "Urbanic", "Pant + Sweatshirt + Boot");
                }else{
                    Toast.makeText(getContext(), "Already Exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myDBHelper.ifExists("outfit2")) {
                    imgView2.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
                    myDBHelper.addIntoCart("outfit2", "2", "1675" , "FashionPH", "Denim + Maxi Dress + Footwear");
                }else{
                    Toast.makeText(getContext(), "Already Exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myDBHelper.ifExists("outfit3")) {
                    imgView3.setImageResource(R.drawable.ic_baseline_shopping_cart2_24);
                    myDBHelper.addIntoCart("outfit3", "3", "1775" , "Urbanic", "Dress + Leather Jacket");
                }else{
                    Toast.makeText(getContext(), "Already Exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}