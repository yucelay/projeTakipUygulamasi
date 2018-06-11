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
    ListView projeListesiListView,listView2;
    projelerDb proje;

    ArrayList<projelerDb> projeListesi =new ArrayList<projelerDb>();
   // ArrayList<Icecek> icecekler = new ArrayList<Icecek>();
    OzelAdaptor adaptor;
    ArrayAdapter<String> veriAdaptoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proje_ekibi);
        db=FirebaseDatabase.getInstance();
        projeListesiListView = findViewById(R.id.projeListesiListView);

      //  veriAdaptoru = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,projeListesi);
      //  projeListesiListView.setAdapter(veriAdaptoru);

      //  icecekler.add(new Icecek("lksdaj",12.45));
      //  icecekler.add(new Icecek("yeniIcecek",16.45));

        adaptor = new OzelAdaptor(this,projeListesi);
        projeListesiListView.setAdapter(adaptor);



        projeleriGetirMetodu();
    }
    public void projeleriGetirMetodu(){
        DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    projeListesi.add(new projelerDb(projeID,projeAdi,"",personelSayisi));
                    adaptor.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
