package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

public class OzelAdaptor extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<projelerDb> list;
    Activity activity;
    public OzelAdaptor(Activity activity, List<projelerDb> mList){
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

        satirView=layoutInflater.inflate(R.layout.projeler_satir, null);

        TextView tv=satirView.findViewById(R.id.textViewProjeID);
        TextView tv2=satirView.findViewById(R.id.textViewProjeAdi);
        Button button=satirView.findViewById(R.id.butonYeniPersonelEkle);

        final projelerDb projeler=list.get(position);

        tv.setText(projeler.getProjeAdi().toString());

        // NumberFormat.getCurrencyInstance().format(0.5); --> 0.5 TL

        tv2.setText(NumberFormat.getCurrencyInstance().format(projeler.getProjeAdi()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, projeler.getProjeAdi().toString() + " aldınız", Toast.LENGTH_SHORT).show();
            }
        });
        String icecekIsim=projeler.getProjeAdi().toString();


        return satirView;
    }
}
