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
    FirebaseDatabase db;

    TextView projeListesiTextView;
    ListView projeListesiListView;
    projelerDb proje;

    ArrayList<String> projeListesi =new ArrayList<String>();
    ArrayAdapter<String> veriAdaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_ekibi);
        db=FirebaseDatabase.getInstance();
        projeListesiTextView = findViewById(R.id.projeListesiTextView);
        projeListesiListView = findViewById(R.id.projeListesiListView);

        veriAdaptoru = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,projeListesi);
        projeListesiListView.setAdapter(veriAdaptoru);
        projeleriGetirMetodu();
    }
    public void projeleriGetirMetodu(){
        DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                   // projeListesiTextView.append(gelenler.getValue(projelerDb.class).getProjeAdi()+"-");
                  //  Toast.makeText(projeEkibiActivity.this,gelenler.getValue(projelerDb.class).getProjeAdi()+"",Toast.LENGTH_LONG).show();
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    projeListesi.add(projeID+""+ "          "+ projeAdi+"           "+personelSayisi);
                    veriAdaptoru.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
