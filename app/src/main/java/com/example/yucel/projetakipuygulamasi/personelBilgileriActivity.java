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

public class personelBilgileriActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private personelListesiAdaptor seciliProjeEkibiAdapter;
    private TextView textView33;
    private ArrayList<personelDb> seciliProjeEkibi = new ArrayList<>();
    private ListView seciliProjeEkibiListView;
    private int gelenId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_bilgileri);
        db = FirebaseDatabase.getInstance();
        seciliProjeEkibiListView = findViewById(R.id.seciliProjeEkibiListView);
        seciliProjeEkibiAdapter = new personelListesiAdaptor(this,seciliProjeEkibi);
        seciliProjeEkibiListView.setAdapter(seciliProjeEkibiAdapter);
        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getInt("intentProjeID");

        TextView proje_baslik_textView;
        Button sayfalar_menu_buton;
        proje_baslik_textView = findViewById(R.id.proje_baslik_textView);
        sayfalar_menu_buton = findViewById(R.id.sayfalar_menu_buton);
        proje_baslik_textView.setText("Personel Listesi ve Bilgileri");

        sayfalar_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi = new Intent(personelBilgileriActivity.this,menuActivity.class);
                startActivity(menuSayfasi);
            }
        });



        projeleriGetirMetodu();
    }


    public void projeleriGetirMetodu(){
        DatabaseReference projeleriGetir=db.getReference("personel");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                seciliProjeEkibi.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(personelDb.class).getProjeID();
                    String personelAdiSoyadi = gelenler.getValue(personelDb.class).getP_adi_soyadi();
                    String personelMail = gelenler.getValue(personelDb.class).getP_mail();
                    String personelKey = gelenler.getValue(personelDb.class).getP_key();

                    if (gelenId==projeID){
                        seciliProjeEkibi.add(new personelDb(projeID,personelAdiSoyadi,"",personelMail,personelKey));
                    }
                    seciliProjeEkibiAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
