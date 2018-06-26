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

public class testEdilmisProjelerActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private ArrayList<testEdilmisProjelerDb> testEdilmisProjeler = new ArrayList<>();
    private List<Integer> projeIdleri = new ArrayList<>();
    private testEdilmisProjelerAdaptor testEdilmisProjelerAdaptor;
    private ArrayAdapter<Integer> arrayAdapter;
    private ListView testEdilmisProjelerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edilmis_projeler);
        db = FirebaseDatabase.getInstance();
        testEdilmisProjelerListView = findViewById(R.id.testEdilmisProjelerListView);
        testEdilmisProjelerAdaptor = new testEdilmisProjelerAdaptor(this, testEdilmisProjeler);
        testEdilmisProjelerListView.setAdapter(testEdilmisProjelerAdaptor);
        projeleriGetirMetodu();


    }

    public void projeleriGetirMetodu() {

        DatabaseReference projeleriGetir = db.getReference("testedilmisprojeler");
        projeleriGetir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("onchange metodu :" + dataSnapshot.getValue());
                for (DataSnapshot gelenler : dataSnapshot.getChildren()) {
                    final int projeID = gelenler.getValue(testEdilmisProjelerDb.class).getProjeID();
                            testEdilmisProjeler.add(new testEdilmisProjelerDb(1, projeID));
                        }
                testEdilmisProjelerAdaptor.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }



}

