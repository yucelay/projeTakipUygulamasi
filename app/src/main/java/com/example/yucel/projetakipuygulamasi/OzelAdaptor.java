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

import java.util.List;

public class OzelAdaptor extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<projelerDb> list;
    private Activity activity;

    public OzelAdaptor(Activity activity, List<projelerDb> mList) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = mList;
        this.activity = activity;
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
        satirView = layoutInflater.inflate(R.layout.projeler_satir, null);
        TextView tvProjeID = satirView.findViewById(R.id.projeIDTextView);
        TextView tvProjeAdi = satirView.findViewById(R.id.projeAdiTextView);
        TextView tvPersonelSayisi = satirView.findViewById(R.id.projePersonelSayisiTextView);
        Button personelEkleButon = satirView.findViewById(R.id.personelEkleButon);
        Button personelBilgileriniGosterButon = satirView.findViewById(R.id.personelBilgileriButon);

        final projelerDb projeler = list.get(position);
        tvProjeID.setText("#" + String.valueOf(projeler.getProjeID()));
        tvProjeAdi.setText(projeler.getProjeAdi().toString());
        tvPersonelSayisi.setText(String.valueOf(projeler.getProjedekiPersonelSayisi()) + " kişi");

        personelEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(activity, personelEkleActivity.class);
                intent1.putExtra("intentProjeID", projeler.getProjeID());
                intent1.putExtra("intentProjeSifre", projeler.getSifre().toString());
                intent1.putExtra("intentProjeAdi", projeler.getProjeAdi());
                activity.startActivity(intent1);
            }
        });

        personelBilgileriniGosterButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, personelBilgileriActivity.class);
                intent.putExtra("intentProjeID", projeler.getProjeID());
                activity.startActivity(intent);

            }
        });


        return satirView;
    }
}
