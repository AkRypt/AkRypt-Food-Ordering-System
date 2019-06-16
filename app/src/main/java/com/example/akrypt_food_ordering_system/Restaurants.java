package com.example.akrypt_food_ordering_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Restaurants extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);

//        Intent intent = getIntent();
//        getIntent();
    }


    public void getRestaurant(View view) {

        Intent intent = new Intent(Restaurants.this, Menus.class);
        switch (view.getId()) {
            case R.id.kfc:
                intent.putExtra("restaurant", "kfc");
                break;
            case R.id.indiblaze:
                intent.putExtra("restaurant", "indiblaze");
                break;
            case R.id.papajohn:
                intent.putExtra("restaurant", "papajohn");
                break;
            case R.id.tacobell:
                intent.putExtra("restaurant", "tacobell");
                break;
        }
        startActivity(intent);

    }
}
