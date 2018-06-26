package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
    int gelenProjeID;

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
