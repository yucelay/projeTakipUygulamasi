package com.example.yucel.projetakipuygulamasi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personelEkleActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private int gelenId;
    private String gelenSifre;
    private Context context = this;
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
        textView44.setText("#"+String.valueOf(gelenId)+" -> "+ gelenProjeAdi);

        final EditText adSoyad,mail;
        Button personelKaydetButon,personelEkleKapatButon;
        adSoyad = findViewById(R.id.personelAdiSoyadiEditText);
        mail = findViewById(R.id.personelEmailEditText);
        personelKaydetButon = findViewById(R.id.personelKaydetButon);
        personelEkleKapatButon = findViewById(R.id.personelKaydetKapatButon);

        personelKaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adSoyad.getText().toString().trim().equals("") || mail.getText().toString().trim().equals("")){
                    Toast.makeText(personelEkleActivity.this, "Lütfen tüm alanları doldurunuz.",Toast.LENGTH_SHORT).show();
                }else{
                    personelEkle(gelenId,adSoyad.getText().toString(),gelenSifre,mail.getText().toString());
                    adSoyad.setText("");
                    mail.setText("");
                }

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


        LayoutInflater li = LayoutInflater.from(context);
        View layout = li.inflate(R.layout.ozel_toast,null);
        TextView toastTextView = layout.findViewById(R.id.toastTextView);
        ImageView toastImageView = layout.findViewById(R.id.toastImageView);
        toastImageView.setImageResource(R.drawable.success);
        toastTextView.setText("Personel Eklendi");

       // toastImageView.animate().translationX(-200).setDuration(1000).setStartDelay(1);
        //toastImageView.animate().rotationX(120).setDuration(1000).setStartDelay(1);
        toastImageView.animate().rotation(360).setDuration(1000).setStartDelay(1);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.setView(layout);
        toast.show();






    }


}
