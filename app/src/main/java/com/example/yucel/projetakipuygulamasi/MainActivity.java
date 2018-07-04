package com.example.yucel.projetakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private Button girisYapButon;
    private String dataBaseMail, dataBaseSifre;
    private EditText editTextEmail, editTextSifre;
    private String android_id;
    private Boolean beniHatirla = false;
    private Boolean checkBoxDurum = false;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        db = FirebaseDatabase.getInstance();
        girisYapButon = findViewById(R.id.girisYapButon);
        editTextSifre = findViewById(R.id.editTextSifre);
        editTextEmail = findViewById(R.id.editTextEmail);
        checkBox = findViewById(R.id.checkBox);

        final DatabaseReference beniHatirlaData = db.getReference("benihatirla");
        beniHatirlaData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("beni hatirla data :" + dataSnapshot.child(android_id).child("email").getValue());

                if (dataSnapshot.child(android_id).child("email").getValue() != null && dataSnapshot.child(android_id).child("sifre").getValue() != null) {
                    editTextEmail.setText(dataSnapshot.child(android_id).child("email").getValue().toString());
                    editTextSifre.setText(dataSnapshot.child(android_id).child("sifre").getValue().toString());
                    checkBoxDurum = true;
                    checkBox.setChecked(checkBoxDurum);
                    beniHatirla = true;
                } else {
                    editTextEmail.setText("");
                    editTextSifre.setText("");
                    checkBoxDurum = false;
                    checkBox.setChecked(checkBoxDurum);
                    beniHatirla = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        final DatabaseReference yoneticiRef = db.getReference("yonetici");
        yoneticiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataBaseMail = dataSnapshot.child("email").getValue().toString();
                dataBaseSifre = dataSnapshot.child("sifre").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        girisYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference yoneticiRef = db.getReference("yonetici");
                yoneticiRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataBaseMail = dataSnapshot.child("email").getValue().toString();
                        dataBaseSifre = dataSnapshot.child("sifre").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                if ((editTextEmail.getText().toString().trim().equals(dataBaseMail)) && (editTextSifre.getText().toString().trim().equals(dataBaseSifre))) {
                    if (beniHatirla == true) {
                        DatabaseReference beniHatirlaRef = db.getReference("benihatirla");
                        beniHatirlaRef.child(android_id).child("cihazId").setValue(android_id);
                        beniHatirlaRef.child(android_id).child("sifre").setValue(editTextSifre.getText().toString());
                        beniHatirlaRef.child(android_id).child("email").setValue(editTextEmail.getText().toString());
                    } else {
                        DatabaseReference beniHatirlaRef = db.getReference("benihatirla");
                        beniHatirlaRef.child(android_id).removeValue();
                        System.out.println("onayli degil");
                    }

                    Intent menuSayfasi = new Intent(MainActivity.this, menuActivity.class);
                    startActivity(menuSayfasi);
                    System.out.println("beni hatırla :" + beniHatirla);
                } else {
                    Toast.makeText(MainActivity.this, "Kullanıcı Bilgilerini Yanlış Girdiniz", Toast.LENGTH_SHORT).show();
                    System.out.println("edittext email :" + editTextEmail.getText());
                    System.out.println("edittext email :" + editTextSifre.getText());
                    System.out.println("database email :" + dataBaseMail);
                    System.out.println("database sifre :" + dataBaseSifre);
                }
            }
        });
    }

    public void itemClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {
            beniHatirla = true;
        } else {
            beniHatirla = false;
        }
    }
}
