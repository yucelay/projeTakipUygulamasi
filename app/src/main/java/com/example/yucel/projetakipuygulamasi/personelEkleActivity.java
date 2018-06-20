package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personelEkleActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private int gelenId;
    private String gelenSifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_ekle);
        db = FirebaseDatabase.getInstance();

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width/1.2),(int)(height/2));

        TextView textView44 = findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getInt("intentProjeID");
        gelenSifre = bundle.getString("intentProjeSifre");
        String gelenProjeAdi = bundle.getString("intentProjeAdi");
        textView44.setText("#"+String.valueOf(gelenId)+" - "+ gelenProjeAdi+" -> "+ gelenSifre + "hhhh");

        final EditText adSoyad,mail;
        Button personelKaydetButon,personelEkleKapatButon;
        adSoyad = findViewById(R.id.personelAdiSoyadiEditText);
        mail = findViewById(R.id.personelEmailEditText);
        personelKaydetButon = findViewById(R.id.personelKaydetButon);
        personelEkleKapatButon = findViewById(R.id.personelKaydetKapatButon);

        personelKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personelEkle(gelenId,adSoyad.getText().toString(),gelenSifre,mail.getText().toString());
            }
        });


        personelEkleKapatButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


    }

    private void personelEkle(int projeID, String p_adi_soyadi, String p_sifre, String p_mail){
        DatabaseReference dbRef=db.getReference("personel");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKey = db.getReference("personel/"+key);
        dbRefKey.setValue(new personelDb(projeID,p_adi_soyadi,p_sifre,p_mail,key));
        Toast.makeText(getApplicationContext(),"Personel eklendi.",Toast.LENGTH_SHORT).show();
    }


}
