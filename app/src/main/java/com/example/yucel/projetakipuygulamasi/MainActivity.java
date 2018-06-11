package com.example.yucel.projetakipuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button girisYapButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girisYapButon=(Button)findViewById(R.id.girisYapButon);


        girisYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuSayfasi=new Intent(MainActivity.this, menuActivity.class);
                startActivity(menuSayfasi);
            }
        });
    }
}
