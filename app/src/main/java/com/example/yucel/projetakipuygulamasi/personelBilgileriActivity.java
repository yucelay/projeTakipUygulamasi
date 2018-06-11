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
import java.util.List;

public class personelBilgileriActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private ArrayAdapter seciliProjeEkibiAdapter;
    private TextView textView33;
    private List<String> seciliProjeEkibi;
    private ListView seciliProjeEkibiListView;
    private int gelenId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_bilgileri);
        db = FirebaseDatabase.getInstance();
        seciliProjeEkibiListView = findViewById(R.id.seciliProjeEkibiListView);

        seciliProjeEkibi = new ArrayList<String>();
        seciliProjeEkibiAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,seciliProjeEkibi);
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
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(personelDb.class).getProjeID();
                    String personelAdiSoyadi = gelenler.getValue(personelDb.class).getP_adi_soyadi();
                    String personelMail = gelenler.getValue(personelDb.class).getP_mail();
                    if (gelenId==projeID){
                        seciliProjeEkibi.add(projeID + " "+personelAdiSoyadi+" "+personelMail);
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
