package com.example.yucel.projetakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class menuActivity extends AppCompatActivity {
    Button yeniProjeOlusturButon,projeEkibiButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        yeniProjeOlusturButon = findViewById(R.id.yeniProjeButton);
        projeEkibiButon = findViewById(R.id.projeEkibiButton);

        yeniProjeOlusturButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yeniProjeSayfasi=new Intent(menuActivity.this,yeniProjeActivity.class);
                startActivity(yeniProjeSayfasi);
            }
        });


        projeEkibiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent projeEkibiSayfasi=new Intent(menuActivity.this,projeEkibiActivity.class);
                startActivity(projeEkibiSayfasi);
            }
        });

    }
}
