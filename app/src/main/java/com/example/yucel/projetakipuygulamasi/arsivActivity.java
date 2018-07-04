package com.example.yucel.projetakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
        arsivArrayAdoptor = new ArrayAdapter(this, R.layout.list_item_new, arsivler);
        arsivdekilerListView.setAdapter(arsivArrayAdoptor);

        TextView proje_baslik_textView;
        Button sayfalar_menu_buton;
        proje_baslik_textView = findViewById(R.id.proje_baslik_textView);
        sayfalar_menu_buton = findViewById(R.id.sayfalar_menu_buton);
        proje_baslik_textView.setText("Arşiv");

        sayfalar_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi = new Intent(arsivActivity.this,menuActivity.class);
                startActivity(menuSayfasi);
            }
        });


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
