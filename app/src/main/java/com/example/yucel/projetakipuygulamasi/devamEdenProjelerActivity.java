package com.example.yucel.projetakipuygulamasi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class devamEdenProjelerActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private List<projelerDb> devamEdenProjeler = new ArrayList<>();
    private devamEdenProjelerAdaptor devamEdenProjelerAdaptor ;
    private ListView devamEdenProjelerListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devam_eden_projeler);
        db = FirebaseDatabase.getInstance();
        devamEdenProjelerListView = findViewById(R.id.devamEdenProjelerListView);
        devamEdenProjelerAdaptor = new devamEdenProjelerAdaptor(this,devamEdenProjeler);
        devamEdenProjelerListView.setAdapter(devamEdenProjelerAdaptor);
        devamEdenProjelerMetodu();
        yapilacakSayisiMethod();



    }

    public void devamEdenProjelerMetodu(){
        DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                devamEdenProjeler.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String tarih = gelenler.getValue(projelerDb.class).getTarih();
                    devamEdenProjeler.add(new projelerDb(projeID,projeAdi,"",personelSayisi,tarih));

                    // Toast.makeText(getApplicationContext(),String.valueOf(gelenler.),Toast.LENGTH_SHORT).show();
                }
                devamEdenProjelerAdaptor.notifyDataSetChanged();
              //  System.out.println("data sayisi :" + dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void yapilacakSayisiMethod(){
        DatabaseReference projeleriGetir=db.getReference("yapilacak").getParent();
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // devamEdenProjeler.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                   /* int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    int personelSayisi = gelenler.getValue(projelerDb.class).getProjedekiPersonelSayisi();
                    String tarih = gelenler.getValue(projelerDb.class).getTarih();
                    devamEdenProjeler.add(new projelerDb(projeID,projeAdi,"",personelSayisi,tarih));

                    // Toast.makeText(getApplicationContext(),String.valueOf(gelenler.),Toast.LENGTH_SHORT).show();*/


                }
              //  System.out.println("yapilacak Sayisi :" + dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
