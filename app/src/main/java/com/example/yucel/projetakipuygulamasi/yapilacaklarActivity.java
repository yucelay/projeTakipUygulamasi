package com.example.yucel.projetakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class yapilacaklarActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private ListView yapilacaklarListView;
    private List<projelerDb> tumProjeler = new ArrayList<>();
    private yapilacaklarAdaptor yapilacaklarListAdapter;
    private String yapilacakAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacaklar);
        yapilacaklarListView = findViewById(R.id.yapilacaklarListView);
        yapilacaklarListAdapter = new yapilacaklarAdaptor(this, tumProjeler);
        yapilacaklarListView.setAdapter(yapilacaklarListAdapter);
        projeleriGetirMetodu();


        TextView proje_baslik_textView;
        Button sayfalar_menu_buton;
        proje_baslik_textView = findViewById(R.id.proje_baslik_textView);
        sayfalar_menu_buton = findViewById(R.id.sayfalar_menu_buton);
        proje_baslik_textView.setText("Yapılacaklar");

        sayfalar_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi = new Intent(yapilacaklarActivity.this,menuActivity.class);
                startActivity(menuSayfasi);
            }
        });


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
                    yapilacaklarListAdapter.notifyDataSetChanged();
                    // Toast.makeText(getApplicationContext(),String.valueOf(gelenler.),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
