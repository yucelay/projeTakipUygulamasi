package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class yapilacaklarDetayActivity extends AppCompatActivity {

   private FirebaseDatabase db;
   private ListView yapilacakDetayListView;
   private List<yapilacaklarDb> yapilacaklarDetay = new ArrayList<>();
   private yapilacakDetayAdaptor yapilacakDetayAdaptor;
   int gelenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacaklar_detay);
        db = FirebaseDatabase.getInstance();

        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getInt("intentProjeID");
        Toast.makeText(this,"detay : "+String.valueOf(gelenId),Toast.LENGTH_SHORT).show();

        yapilacakDetayListView = findViewById(R.id.yapilacakDetayListView);
        yapilacakDetayAdaptor = new yapilacakDetayAdaptor(this,yapilacaklarDetay);
        yapilacakDetayListView.setAdapter(yapilacakDetayAdaptor);
        projeleriGetirMetodu();
    }

    public void projeleriGetirMetodu(){
        DatabaseReference projeleriGetir=db.getReference("yapilacak");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yapilacaklarDetay.clear();
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    int projeID = gelenler.getValue(yapilacaklarDb.class).getProjeId();
                    int onay = gelenler.getValue(yapilacaklarDb.class).getOnay();
                    String yapilacakAdi = gelenler.getValue(yapilacaklarDb.class).getYapilacakAdi();
                    if (gelenId==projeID){
                        yapilacaklarDetay.add(new yapilacaklarDb(onay,projeID,yapilacakAdi));
                        yapilacakDetayAdaptor.notifyDataSetChanged();
                }
            }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}



