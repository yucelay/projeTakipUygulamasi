package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
        textView33 = findViewById(R.id.textView3);
        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getInt("intentProjeID");
        textView33.setText(String.valueOf(gelenId));

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
