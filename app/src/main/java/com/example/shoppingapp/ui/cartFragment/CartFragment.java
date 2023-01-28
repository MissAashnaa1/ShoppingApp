package com.example.shoppingapp.ui.cartFragment;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shoppingapp.MyDBHelper;
import com.example.shoppingapp.R;
import com.example.shoppingapp.TableModel;

import java.util.ArrayList;


public class CartFragment extends Fragment {
    Button btnPlaceOrder;
    MyDBHelper myDBHelper;
    TextView empty;
    TextView txtView;

    public CartFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container,false);

        getActivity().setTitle("Cart");

        empty = view.findViewById(R.id.textViewCartEmpty);
        myDBHelper = new MyDBHelper(getContext());

        // call function to print data on cart fragment screen
        if(myDBHelper.isItemInCart()) {
            ArrayList<TableModel> arrayList = myDBHelper.getAllOutfit();
            for (int i = 0; i < arrayList.size(); i++) {
                Log.d("OUTFIT",
                        "outfit: " + arrayList.get(i).outfit +
                        " Value " + arrayList.get(i).value +
                        " Price " + arrayList.get(i).price +
                        " Name " + arrayList.get(i).name+
                        " Name2 " + arrayList.get(i).name2);
            }
            empty.setText("");
            txtView = view.findViewById(R.id.textViewCart);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                stringBuilder.append("\n\n"+ arrayList.get(i).name + "\n"+ arrayList.get(i).name2 + "\nPrice: "+arrayList.get(i).price);
            }
            txtView.setText(stringBuilder);
        }
        else{
            empty.setText("No items in your cart");
        }

        btnPlaceOrder = view.findViewById(R.id.buttonPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDBHelper.isItemInCart()){
                    myDBHelper.deleteTableCart();
                    empty.setText("No items in your cart");
                    txtView.setText("");
                    Toast.makeText(getContext(), "Your order has successfully placed", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "No items in your cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}