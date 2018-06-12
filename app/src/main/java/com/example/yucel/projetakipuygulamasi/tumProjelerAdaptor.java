package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class tumProjelerAdaptor extends BaseAdapter {
    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<projelerDb> list;
    private Activity activity;
    private Button projeyiSilButon;
    public tumProjelerAdaptor(Activity activity, List<projelerDb> mList){
        db=FirebaseDatabase.getInstance();
        layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list=mList;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView=layoutInflater.inflate(R.layout.tum_projeler_satir, null);
        TextView tvProjeID = satirView.findViewById(R.id.projeIDTextView);
        TextView tvProjeAdi = satirView.findViewById(R.id.projeAdiTextView);
        TextView tvTarih = satirView.findViewById(R.id.projeTarih);
        Button gorevleriGosterButon = satirView.findViewById(R.id.gorevleriGosterButon);
        projeyiSilButon = satirView.findViewById(R.id.projeyiSilButon);

        final projelerDb projeler=list.get(position);
        tvProjeID.setText("#"+String.valueOf(projeler.getProjeID()));
        tvProjeAdi.setText(projeler.getProjeAdi().toString());
        tvTarih.setText(String.valueOf(projeler.getTarih()));

        gorevleriGosterButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, projeler.getProjeID() + " id li proje secildi", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(activity, gorevlerActivity.class);
                intent1.putExtra("intentProjeID",projeler.getProjeID());
                intent1.putExtra("intentProjeAdi",projeler.getProjeAdi());
                activity.startActivity(intent1);
            }
        });

        projeyiSilButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref=db.getReference("projeler");
                ref.child(String.valueOf(projeler.getProjeID())).removeValue();
                Toast.makeText(tumProjelerAdaptor.this.activity,"Proje Başarılı Bir Şekilde Silinmiştir.",Toast.LENGTH_SHORT).show();
            }
        });


        return satirView;
    }


}