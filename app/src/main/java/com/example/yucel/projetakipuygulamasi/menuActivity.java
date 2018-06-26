package com.example.yucel.projetakipuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class menuActivity extends AppCompatActivity {
    private Button yeniProjeOlusturButon,projeEkibiButon,tumProjelerButon,yapilacaklarButon,devamEdenProjelerButon,testEdilmisProjelerButon;
    private Button tamamlanmisProjelerButon,menuArsivButon;
    private Button projeOlusturKapatButon,projeOlusturButon;
    private FirebaseDatabase db;
    private EditText projeID,projeAdi,sifre,personelSayisi;
    private int projeIDnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db = FirebaseDatabase.getInstance();

        yeniProjeOlusturButon = findViewById(R.id.yeniProjeButton);
        projeEkibiButon = findViewById(R.id.projeEkibiButton);
        tumProjelerButon = findViewById(R.id.tumProjelerButton);
        yapilacaklarButon = findViewById(R.id.yapilacaklarButon);
        devamEdenProjelerButon = findViewById(R.id.devamEdenProjelerButon);
        tamamlanmisProjelerButon = findViewById(R.id.tamamlanmisProjelerButon);
        testEdilmisProjelerButon = findViewById(R.id.testEdilmisProjelerButon);
        menuArsivButon = findViewById(R.id.menuArsivButon);




        yeniProjeOlusturButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent yeniProjeSayfasi = new Intent(menuActivity.this,yeniProjeActivity.class);
                startActivity(yeniProjeSayfasi);*/
               showPopup();
            }
        });


        projeEkibiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent projeEkibiSayfasi=new Intent(menuActivity.this,projeEkibiActivity.class);
                startActivity(projeEkibiSayfasi);
            }
        });

        tumProjelerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tumProjelerSayfasi = new Intent(menuActivity.this, tumProjelerActivity.class);
                startActivity(tumProjelerSayfasi);

            }
        });

        yapilacaklarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yapilacaklarSayfasi = new Intent(menuActivity.this, yapilacaklarActivity.class);
                startActivity(yapilacaklarSayfasi);
            }
        });

        devamEdenProjelerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent devamEdenProjeler = new Intent(menuActivity.this, devamEdenProjelerActivity.class);
                startActivity(devamEdenProjeler);
            }
        });

        tamamlanmisProjelerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tamamlanmisProjelerSayfasi = new Intent(menuActivity.this, tamamlanmisProjelerActivity.class);
                startActivity(tamamlanmisProjelerSayfasi);
            }
        });

        testEdilmisProjelerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testEdilmisProjelerSayfasi = new Intent(menuActivity.this, testEdilmisProjelerActivity.class);
                startActivity(testEdilmisProjelerSayfasi);
            }
        });


        menuArsivButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent arsivSayfasi = new Intent(menuActivity.this, arsivActivity.class);
                startActivity(arsivSayfasi);
            }
        });

    }


    private PopupWindow pw;
    private void showPopup() {
        try {
            LayoutInflater inflater = (LayoutInflater) menuActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.proje_ekle_popup,
                    (ViewGroup) findViewById(R.id.popup_1));

            DisplayMetrics dm=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width=dm.widthPixels;
            int height=dm.heightPixels;
           // getWindow().setLayout((int)(width/1.2),(int)(height/2));


            pw = new PopupWindow(layout,(int)(width / (1.2)), height/2, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            projeOlusturKapatButon = layout.findViewById(R.id.projeOlusturKapatButon);
            projeOlusturButon = layout.findViewById(R.id.projeOlusturButon);
            projeID =layout.findViewById(R.id.projeidEditText);
            projeAdi = layout.findViewById(R.id.projeAdiEditText);
            sifre =layout.findViewById(R.id.sifreEditText);
            projeOlusturButon.setOnClickListener(proje_olustur_buton);
            projeOlusturKapatButon.setOnClickListener(cancel_button);

            Random r1 = new Random();
            int random1 = (r1.nextInt(9900) + 1000);
            Random r2 = new Random();
            projeIDnum = (r2.nextInt(99) + 1);
            projeIDnum += random1;
            projeID.setText(String.valueOf(projeIDnum));
            projeID.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };

    private View.OnClickListener proje_olustur_buton = new View.OnClickListener() {
        public void onClick(View v) {

            if(projeID.getText().toString().trim().equals("") ||
                    projeAdi.getText().toString().trim().equals("") ||
                    sifre.getText().toString().trim().equals("")){
                Toast.makeText(menuActivity.this,"Lütfen tüm alanları doldurunuz.",Toast.LENGTH_SHORT).show();
            }else{
                Date simdikiZaman = new Date();
                System.out.println(simdikiZaman.toString());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String tarih=(df.format(simdikiZaman));
                projeEkle(Integer.parseInt(projeID.getText().toString()), projeAdi.getText().toString(), sifre.getText().toString(), 0,tarih);
                projeID.setText("");
                projeAdi.setText("");
                sifre.setText("");
                Toast.makeText(menuActivity.this,"Kayıt İşlemi Başarılı.",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void projeEkle(int projeID, String projeAdi, String sifre, int personelSayisi,String tarih){
        DatabaseReference dbRef=db.getReference("projeler");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKey = db.getReference("projeler/"+projeIDnum);
        dbRefKey.setValue(new projelerDb(projeID,projeAdi,sifre,personelSayisi,tarih));
    }


}
