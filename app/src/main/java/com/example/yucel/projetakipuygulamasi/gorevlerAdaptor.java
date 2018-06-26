package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class gorevlerAdaptor extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<gorevlerDb> list;
    private Activity activity;
    public gorevlerAdaptor(Activity activity, List<gorevlerDb> mList){
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
        satirView=layoutInflater.inflate(R.layout.gorevler_satir, null);
        TextView tvGorevBasligi = satirView.findViewById(R.id.gorevBaslikTextView);
        TextView tvGorevAciklamasi = satirView.findViewById(R.id.gorevAciklamasiTextView);
        TextView tvGorevTarihi = satirView.findViewById(R.id.gorevTarihiTextView);


        final gorevlerDb gorevler=list.get(position);
        tvGorevBasligi.setText(gorevler.getGorev_basligi());
        tvGorevAciklamasi.setText(gorevler.getGorev_aciklamasi().toString());
        tvGorevTarihi.setText(gorevler.getGorev_tarihi());



        return satirView;
    }

}
