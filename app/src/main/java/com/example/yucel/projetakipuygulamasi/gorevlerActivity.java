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

public class gorevlerActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private List gorevlerListesi = new ArrayList();
    private gorevlerAdaptor gorevlerAdaptor ;
    private ListView gorevlerListView;
    private int gelenProjeID;
    private String gelenProjeAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorevler);
        gorevlerListView = findViewById(R.id.gorevlerListView);
        db = FirebaseDatabase.getInstance();
        gorevlerAdaptor = new gorevlerAdaptor(gorevlerActivity.this,gorevlerListesi);
        gorevlerListView.setAdapter(gorevlerAdaptor);
        Bundle gorevlerBundle = getIntent().getExtras();
        gelenProjeID = gorevlerBundle.getInt("intentProjeID");
        gelenProjeAdi = gorevlerBundle.getString("intentProjeAdi");

        TextView proje_baslik_textView;
        Button sayfalar_menu_buton;
        proje_baslik_textView = findViewById(R.id.proje_baslik_textView);
        sayfalar_menu_buton = findViewById(R.id.sayfalar_menu_buton);
        proje_baslik_textView.setText(String.valueOf(gelenProjeID)+" "+gelenProjeAdi);

        sayfalar_menu_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi = new Intent(gorevlerActivity.this,tumProjelerActivity.class);
                startActivity(menuSayfasi);
            }
        });

        tumGorevlerMethod();
    }

    public void tumGorevlerMethod(){
        DatabaseReference yapilacaklariGetir=db.getReference("gorevler");
        yapilacaklariGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gorevlerListesi.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(gorevlerDb.class).getProjeId();
                    String gorevTarihi = gelenler.getValue(gorevlerDb.class).getGorev_tarihi();
                    String gorevBasligi = gelenler.getValue(gorevlerDb.class).getGorev_basligi();
                    String gorevAciklamasi = gelenler.getValue(gorevlerDb.class).getGorev_aciklamasi();

                    if(gelenProjeID == projeID){
                        gorevlerListesi.add(new gorevlerDb(projeID,gorevTarihi,gorevBasligi,gorevAciklamasi));
                    }
                }
                gorevlerAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
