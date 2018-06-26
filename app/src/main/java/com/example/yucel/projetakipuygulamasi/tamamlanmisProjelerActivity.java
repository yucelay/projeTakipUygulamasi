package com.example.yucel.projetakipuygulamasi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tamamlanmisProjelerActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    List<projelerDb> tamamlanmisProjeler = new ArrayList<>();
    private List<String> yeniList = new ArrayList<>();
    private tamamlanmisProjelerAdaptor tamamlanmisProjelerAdaptor;
    private ArrayAdapter yeniArrayAdapter;
    private ListView tamamlanmisProjelerListView;
    private int yapilacakSayisi = 0;
    private int yapilacakSayisiOnayli = 0, tamamlanmaYuzdesi = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamamlanmis_projeler);
        db = FirebaseDatabase.getInstance();
        tamamlanmisProjelerListView = findViewById(R.id.tamamlanmisProjelerListView);
        tamamlanmisProjelerAdaptor = new tamamlanmisProjelerAdaptor(this, tamamlanmisProjeler);
        tamamlanmisProjelerListView.setAdapter(tamamlanmisProjelerAdaptor);



        DatabaseReference projeleriGetir = db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tamamlanmisProjeler.clear();
                yeniList.clear();
                for (DataSnapshot gelenler : dataSnapshot.getChildren()) {
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String tarih = gelenler.getValue(projelerDb.class).getTarih();
                   yapilacakSayisiMethod(projeID, projeAdi, personelSayisi, tarih);

                  //  tamamlanmisProjeler.add(new projelerDb(projeID, projeAdi, "", personelSayisi, tarih));
                }
                tamamlanmisProjelerAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void yapilacakSayisiMethod(final int methodProjeID, final String projeAdiMethod, final int personelSayisiMethod, final String tarihMethod) {
        DatabaseReference yapilacakGetir = db.getReference("yapilacak");
        yapilacakGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                yapilacakSayisi = 0;
                yapilacakSayisiOnayli = 0;
                tamamlanmaYuzdesi = 0;
                for (DataSnapshot yapilacaklar : dataSnapshot.getChildren()) {
                    int projeID = yapilacaklar.getValue(yapilacaklarDb.class).getProjeId();
                    int onay = yapilacaklar.getValue(yapilacaklarDb.class).getOnay();
                    String yapilacakAdi = yapilacaklar.getValue(yapilacaklarDb.class).getYapilacakAdi();
                    System.out.println(methodProjeID + " - " + projeID + " - " + onay);
                    if (methodProjeID == projeID) {
                        yapilacakSayisi++;
                    }
                    if (methodProjeID == projeID && onay == 1) {
                        yapilacakSayisiOnayli++;
                    }
                }

                if (yapilacakSayisi == yapilacakSayisiOnayli && yapilacakSayisi != 0) {
                    tamamlanmisProjeler.add(new projelerDb(methodProjeID, projeAdiMethod, "", personelSayisiMethod, tarihMethod));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void setTamamlanmisProjelerMethod2() {

        DatabaseReference projeleriGetir = db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tamamlanmisProjeler.clear();
                yeniList.clear();
                for (DataSnapshot gelenler : dataSnapshot.getChildren()) {
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String tarih = gelenler.getValue(projelerDb.class).getTarih();
                    yapilacakSayisiMethod(projeID, projeAdi, personelSayisi, tarih);
                }
                tamamlanmisProjelerAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
