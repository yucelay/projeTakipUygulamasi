package com.example.yucel.projetakipuygulamasi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tumProjelerActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private ArrayList<projelerDb> tumProjeler = new ArrayList<>();
    private tumProjelerAdaptor tumProjelerAdaptor;
    private ListView tumProjelerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_projeler);
        db = FirebaseDatabase.getInstance();
        tumProjelerListView = findViewById(R.id.tumProjelerListView);
        tumProjelerAdaptor = new tumProjelerAdaptor(this,tumProjeler);
        tumProjelerListView.setAdapter(tumProjelerAdaptor);
        projeleriGetirMetodu();
    }

    public void projeleriGetirMetodu(){
        DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tumProjeler.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String tarih = gelenler.getValue(projelerDb.class).getTarih();
                    tumProjeler.add(new projelerDb(projeID,projeAdi,"",personelSayisi,tarih));
                    tumProjelerAdaptor.notifyDataSetChanged();
                    // Toast.makeText(getApplicationContext(),String.valueOf(gelenler.),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
