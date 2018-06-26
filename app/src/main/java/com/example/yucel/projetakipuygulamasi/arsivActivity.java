package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class arsivActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private ListView arsivdekilerListView;
    private List<String> arsivler = new ArrayList<>();
    private ArrayAdapter arsivArrayAdoptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arsiv);
        db = FirebaseDatabase.getInstance();
        arsivdekilerListView = findViewById(R.id.arsivdekilerListView);
        arsivArrayAdoptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arsivler);
        arsivdekilerListView.setAdapter(arsivArrayAdoptor);


        DatabaseReference ref = db.getReference("arsiv");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot arsivdekiler: dataSnapshot.getChildren()){
                    arsivler.add(arsivdekiler.child("projeId").getValue().toString()+"      -       "+arsivdekiler.child("projeAdi").getValue().toString());
                }
                arsivArrayAdoptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
