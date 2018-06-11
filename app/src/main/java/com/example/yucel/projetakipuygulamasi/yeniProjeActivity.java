package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class yeniProjeActivity extends AppCompatActivity {

    FirebaseDatabase db;
    EditText projeID,projeAdi,sifre,personelSayisi;
    Button projeOLusturButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_proje_grubu_olustur);

        db=FirebaseDatabase.getInstance();
        projeID = findViewById(R.id.projeidEditText);
        projeAdi = findViewById(R.id.projeAdiEditText);
        sifre = findViewById(R.id.sifreEditText);
        personelSayisi = findViewById(R.id.personelSayisiEditText);
        projeOLusturButon=findViewById(R.id.projeOlusturButon);

        projeOLusturButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(projeID.getText().toString()=="" && projeAdi.getText().toString()==""){

                }else{
                    projeEkle(Integer.parseInt(projeID.getText().toString()), projeAdi.getText().toString(), sifre.getText().toString(), Integer.parseInt(personelSayisi.getText().toString()));
                    projeID.setText("");
                    projeAdi.setText("");
                    sifre.setText("");
                    personelSayisi.setText("");
                    projeOLusturButon.setText("");
                }

            }
        });


    }

    private void projeEkle(int projeID, String projeAdi, String sifre, int personelSayisi){
        DatabaseReference dbRef=db.getReference("projeler");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKey = db.getReference("projeler/"+key);
        dbRefKey.setValue(new projelerDb(projeID,projeAdi,sifre,personelSayisi));
    }
}
