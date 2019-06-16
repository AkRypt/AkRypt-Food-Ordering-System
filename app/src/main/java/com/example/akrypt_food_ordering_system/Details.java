package com.example.akrypt_food_ordering_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    int totalPrice;
    ArrayList<String> itemNames = new ArrayList<>();
    String restaurant;

    EditText fullName, phNo, city;
    RadioGroup payment;
    RadioButton paymentType;
    Button orderBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        totalPrice = getIntent().getExtras().getInt("totalPrice");
        itemNames = (ArrayList<String>)getIntent().getSerializableExtra("itemNames");
        restaurant = getIntent().getStringExtra("restaurant");

        fullName = findViewById(R.id.fullName);
        phNo = findViewById(R.id.phNo);
        city = findViewById(R.id.city);
        payment = findViewById(R.id.payment);
        orderBtn = findViewById(R.id.order);



        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paymentType = findViewById(payment.getCheckedRadioButtonId());

                if (!fullName.getText().equals("") && !phNo.equals("") && !city.equals("") && paymentType != null) {
                    Intent intent = new Intent(Details.this, Bill.class);
                    intent.putExtra("totalPrice", totalPrice);
                    intent.putExtra("itemNames", itemNames);
                    intent.putExtra("fullName", fullName.getText().toString().trim());
                    intent.putExtra("phNo", phNo.getText().toString().trim());
                    intent.putExtra("city", city.getText().toString().trim());
                    intent.putExtra("restaurant", restaurant);
                    intent.putExtra("paymentType", paymentType.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(Details.this, "Please respond to all the fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
