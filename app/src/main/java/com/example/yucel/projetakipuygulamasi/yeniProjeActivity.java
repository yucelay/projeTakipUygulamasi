package com.example.yucel.projetakipuygulamasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class yeniProjeActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private EditText projeID,projeAdi,sifre,personelSayisi;
    private Button projeOLusturButon,projeOLusturKapatButon;
    private int projeIDnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_proje_grubu_olustur);
        db=FirebaseDatabase.getInstance();
        projeID = findViewById(R.id.projeidEditText);
        projeAdi = findViewById(R.id.projeAdiEditText);
        sifre = findViewById(R.id.sifreEditText);
        projeOLusturButon=findViewById(R.id.projeOlusturButon);
        projeOLusturKapatButon = findViewById(R.id.projeOlusturKapatButon);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width/1.2),(int)(height/2));

        Random r1 = new Random();
        int random1 = (r1.nextInt(9900) + 1000);
        Random r2 = new Random();
        projeIDnum = (r2.nextInt(99) + 1);
        projeIDnum += random1;
        projeID.setText(String.valueOf(projeIDnum));
        projeID.setEnabled(false);
        projeOLusturButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(projeID.getText().toString().trim().equals("") ||
                        projeAdi.getText().toString().trim().equals("") ||
                        sifre.getText().toString().trim().equals("")){
                    Toast.makeText(yeniProjeActivity.this,"Lütfen tüm alanları doldurunuz.",Toast.LENGTH_SHORT).show();
                }else{
                    Date simdikiZaman = new Date();
                    System.out.println(simdikiZaman.toString());
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String tarih=(df.format(simdikiZaman));
                    projeEkle(Integer.parseInt(projeID.getText().toString()), projeAdi.getText().toString(), sifre.getText().toString(), 0,tarih);
                    projeID.setText("");
                    projeAdi.setText("");
                    sifre.setText("");
                    Toast.makeText(yeniProjeActivity.this,"Kayıt İşlemi Başarılı.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        projeOLusturKapatButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void projeEkle(int projeID, String projeAdi, String sifre, int personelSayisi,String tarih){
        DatabaseReference dbRef=db.getReference("projeler");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKey = db.getReference("projeler/"+projeIDnum);
        dbRefKey.setValue(new projelerDb(projeID,projeAdi,sifre,personelSayisi,tarih));
    }
}
