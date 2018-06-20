package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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
    private ListView projeListesiListView,listView2;
    private projelerDb proje;
    private ArrayList<projelerDb> projeListesi =new ArrayList<projelerDb>();
   // ArrayList<Icecek> icecekler = new ArrayList<Icecek>();
    private OzelAdaptor adaptor;
    private ArrayAdapter<String> veriAdaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_ekibi);
        db=FirebaseDatabase.getInstance();
        projeListesiListView = findViewById(R.id.projeListesiListView);

        adaptor = new OzelAdaptor(this,projeListesi);
        projeListesiListView.setAdapter(adaptor);

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

                   // Toast.makeText(getApplicationContext(),String.valueOf(gelenler.),Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
