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

public class projeEkibiActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private TextView projeListesiTextView;
    private ListView projeListesiListView;
    private ArrayList<projelerDb> projeListesi =new ArrayList<projelerDb>();
    private OzelAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_ekibi);
        db=FirebaseDatabase.getInstance();
        projeListesiListView = findViewById(R.id.projeListesiListView);

        adaptor = new OzelAdaptor(this,projeListesi);
        projeListesiListView.setAdapter(adaptor);



        TextView proje_baslik_textView;
        Button sayfalar_menu_buton;
        proje_baslik_textView = findViewById(R.id.proje_baslik_textView);
        sayfalar_menu_buton = findViewById(R.id.sayfalar_menu_buton);
        proje_baslik_textView.setText("Proje Ekibi");

        sayfalar_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi = new Intent(projeEkibiActivity.this,menuActivity.class);
                startActivity(menuSayfasi);
            }
        });








        projeleriGetirMetodu();
    }
    public void projeleriGetirMetodu(){

        DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projeListesi.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String projeSifre = gelenler.getValue(projelerDb.class).getSifre();
                    projeListesi.add(new projelerDb(projeID,projeAdi,projeSifre,personelSayisi,""));
                    adaptor.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
