package com.example.yucel.projetakipuygulamasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_ekle);
        db = FirebaseDatabase.getInstance();

        TextView textView44 = findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();
        gelenId = bundle.getInt("intentProjeID");
        String gelenProjeAdi = bundle.getString("intentProjeAdi");
        textView44.setText("#"+String.valueOf(gelenId)+" - "+ gelenProjeAdi);

        final EditText adSoyad,mail;
        Button personelKaydetButon;
        adSoyad = findViewById(R.id.personelAdiSoyadiEditText);
        mail = findViewById(R.id.personelEmailEditText);
        personelKaydetButon = findViewById(R.id.personelKaydetButon);

        personelKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personelEkle(gelenId,adSoyad.getText().toString(),"sifre123",mail.getText().toString());
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
