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

public class tamamlanmisProjelerAdaptor extends BaseAdapter {
    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<projelerDb> list;
    private Activity activity;
    private Button projeyiSilButon;
    private ImageView yapilacakDetayImageView;
    private int yapilacakSayisi = 0;
    private int yapilacakSayisiOnayli = 0, tamamlanmaYuzdesi = 0;

    public tamamlanmisProjelerAdaptor(Activity activity, List<projelerDb> mList){
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
        satirView = layoutInflater.inflate(R.layout.tamamlanmis_projeler_satir, null);
        final TextView tvProjeID = satirView.findViewById(R.id.tamamlanmisProjeIDTextView);
        final TextView tvProjeAdi = satirView.findViewById(R.id.tamamlanmisProjeAdiTextView);
        final TextView tvprojeTamamlanmaTarihi =satirView.findViewById(R.id.projeTamamlanmaTarihTextView);
       // final TextView tvProjeTamamlanmaOrani = satirView.findViewById(R.id.projeTamamlanmaOraniTextView);
        Button gorevleriGosterButon = satirView.findViewById(R.id.gorevleriGosterButon);
        projeyiSilButon = satirView.findViewById(R.id.projeyiSilButon);
        yapilacakDetayImageView = satirView.findViewById(R.id.yapilacakDetayImageView);

        final projelerDb devamEdenler = list.get(position);

                    tvProjeID.setText("#" + String.valueOf(devamEdenler.getProjeID()));
                    tvProjeAdi.setText(devamEdenler.getProjeAdi());
        return satirView;

    }

}
