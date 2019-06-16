package com.example.akrypt_food_ordering_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Menus extends AppCompatActivity {

    private DatabaseReference dbRef;

    private ListView items;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<Integer> itemValues = new ArrayList<>();
    private ArrayList<String> finalItemNames = new ArrayList<>();

    private TextView tPrice;
    private Button proceedBtn;
    private ProgressBar loading;
    private int totalPrice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus);
        final String restaurant = getIntent().getStringExtra("restaurant");

        tPrice = findViewById(R.id.price);
        proceedBtn = findViewById(R.id.proceed);
        loading = findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        dbRef = FirebaseDatabase.getInstance().getReference().child(restaurant);
        items = findViewById(R.id.items);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemNames);

        items.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        items.setAdapter(arrayAdapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String item = ds.child("name").getValue(String.class);
                    itemNames.add(item);
                    arrayAdapter.notifyDataSetChanged();
                    itemValues.add(ds.child("price").getValue(Integer.class));
                }
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                totalPrice = 0;
                finalItemNames.clear();

                SparseBooleanArray itemsSelected = items.getCheckedItemPositions();
                for (int index = 0; index < itemsSelected.size(); index++) {
                    int key = itemsSelected.keyAt(index);
                    if (itemsSelected.get(key)) {
                        totalPrice = totalPrice + itemValues.get(key);
                        finalItemNames.add(itemNames.get(key));
                    }
                }
                tPrice.setText("Total: "+totalPrice);
            }
        });


        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menus.this, Details.class);
                intent.putExtra("totalPrice", totalPrice);
                intent.putExtra("itemNames", finalItemNames);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            }
        });

    }
}