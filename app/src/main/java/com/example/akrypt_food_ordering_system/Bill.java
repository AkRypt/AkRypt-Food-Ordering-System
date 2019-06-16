package com.example.akrypt_food_ordering_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Bill extends AppCompatActivity {

    int totalPrice;
    ArrayList<String> itemNames = new ArrayList<>();
    String fullName, phNo, city, paymentType, restaurant;

    TextView billName, billPhNo, billCity, billRest, billItems, billPayment, billTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        totalPrice = getIntent().getExtras().getInt("totalPrice");
        itemNames = (ArrayList<String>)getIntent().getSerializableExtra("itemNames");
        fullName = getIntent().getStringExtra("fullName");
        phNo = getIntent().getStringExtra("phNo");
        city = getIntent().getStringExtra("city");
        paymentType = getIntent().getStringExtra("paymentType");
        restaurant = getIntent().getStringExtra("restaurant");


        billName = findViewById(R.id.billName);
        billPhNo = findViewById(R.id.billPhNo);
        billCity = findViewById(R.id.billCity);
        billRest = findViewById(R.id.billRest);
        billItems = findViewById(R.id.billItems);
        billPayment = findViewById(R.id.billPayment);
        billTotal = findViewById(R.id.billTotal);

        billName.append(fullName);
        billPhNo.append(phNo);
        billCity.append(city);
        billRest.append(restaurant.toUpperCase());
        billPayment.append(paymentType);
        billTotal.setText("Total:      "+totalPrice);

        StringBuilder builder = new StringBuilder();
        for (String name : itemNames) {
            builder.append(name + "\n");
        }
        billItems.setText("Items ordered:\n"+builder.toString());
    }

    public void finish(View view) {
        startActivity(new Intent(Bill.this, Restaurants.class));
    }
}
