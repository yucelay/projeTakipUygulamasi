package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class yapilacakDetayAdaptor extends BaseAdapter {

    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<yapilacaklarDb> list;
    private Activity activity;
    private Button projeyiSilButon;
    private ImageView yapilacakDetayImageView;
    public yapilacakDetayAdaptor(Activity activity, List<yapilacaklarDb> mList){
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
        satirView = layoutInflater.inflate(R.layout.yapilacaklar_detay_satir, null);
        TextView tvProjeID = satirView.findViewById(R.id.projeIDTextView);
        TextView tvProjeAdi = satirView.findViewById(R.id.projeAdiTextView);
        TextView tvTarih = satirView.findViewById(R.id.projeTarih);
        Button gorevleriGosterButon = satirView.findViewById(R.id.gorevleriGosterButon);
        projeyiSilButon = satirView.findViewById(R.id.projeyiSilButon);
        yapilacakDetayImageView = satirView.findViewById(R.id.yapilacakDetayImageView);

        final yapilacaklarDb yapilacaklar = list.get(position);
        tvProjeID.setText("#" + String.valueOf(yapilacaklar.getProjeId()));
        tvProjeAdi.setText(yapilacaklar.getYapilacakAdi().toString());
        // tvTarih.setText(String.valueOf(projeler.getTarih()));



        // yapilacakDetayImageView.setImageResource(R.drawable.ok);

        if(yapilacaklar.getOnay()==1){
            yapilacakDetayImageView.setImageResource(R.drawable.ok);
        }else {
            yapilacakDetayImageView.setImageResource(R.drawable.not_ok);
        }



        return satirView;

    }

}
