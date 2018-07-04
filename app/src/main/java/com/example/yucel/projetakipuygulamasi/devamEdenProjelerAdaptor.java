package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class devamEdenProjelerAdaptor extends BaseAdapter {

    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<projelerDb> list;
    private Activity activity;
    private Button projeyiSilButon;
    private ImageView yapilacakDetayImageView;
    public int yapilacakSayisi = 0;
    public int yapilacakSayisiOnayli = 0, tamamlanmaYuzdesi = 0;

    public devamEdenProjelerAdaptor(Activity activity, List<projelerDb> mList){
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
        satirView = layoutInflater.inflate(R.layout.devam_eden_projeler_satir, null);
        final TextView tvProjeID = satirView.findViewById(R.id.devamEdenProjeIDTextView);
        TextView tvProjeAdi = satirView.findViewById(R.id.devamEdenProjeAdiTextView);
        final ProgressBar progresTamamlanma = satirView.findViewById(R.id.progresTamamlanma);
        final TextView tvYapilacaklarOnaySayisi =satirView.findViewById(R.id.yapilacaklarOnaySayisiTextView);
        final TextView tvProjeTamamlanmaOrani = satirView.findViewById(R.id.projeTamamlanmaOraniTextView);
        final TextView projeTamamlanmaOraniTextView2 = satirView.findViewById(R.id.projeTamamlanmaOraniTextView2);
        Button gorevleriGosterButon = satirView.findViewById(R.id.gorevleriGosterButon);
        projeyiSilButon = satirView.findViewById(R.id.projeyiSilButon);
        yapilacakDetayImageView = satirView.findViewById(R.id.yapilacakDetayImageView);

        final projelerDb devamEdenler = list.get(position);
        tvProjeID.setText("#" + String.valueOf(devamEdenler.getProjeID()));
        tvProjeAdi.setText(devamEdenler.getProjeAdi());
       // tvProjeTamamlanmaOrani.setText(String.valueOf(Math.random()));



        final DatabaseReference projeleriGetir=db.getReference("yapilacak");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int projeID=0;
                yapilacakSayisi=0;
                yapilacakSayisiOnayli = 0;
                tamamlanmaYuzdesi = 0;
                for (DataSnapshot gelenler:dataSnapshot.getChildren()){
                    projeID = gelenler.getValue(yapilacaklarDb.class).getProjeId();
                    int onay = gelenler.getValue(yapilacaklarDb.class).getOnay();
                    String yapilacakAdi = gelenler.getValue(yapilacaklarDb.class).getYapilacakAdi();
                    if (devamEdenler.getProjeID()==projeID){
                        yapilacakSayisi++;
                    }
                    if (devamEdenler.getProjeID()==projeID && onay == 1){
                        yapilacakSayisiOnayli++;
                    }
                }
                if(!(yapilacakSayisi == 0)){
                    tamamlanmaYuzdesi =(int)((yapilacakSayisiOnayli * 100)/yapilacakSayisi);
                    System.out.println("tamamlanma yuzdesi :" +tamamlanmaYuzdesi);
                    tvYapilacaklarOnaySayisi.setText(yapilacakSayisi +" adet yapılacaktan "+ yapilacakSayisiOnayli+" tanesi onaylı.");

                   if(tamamlanmaYuzdesi==100){
                       tvProjeTamamlanmaOrani.setTextColor(Color.parseColor("#03AD03"));
                   }else{
                       tvProjeTamamlanmaOrani.setTextColor(Color.parseColor("#E11F26"));
                   }
                    tvProjeTamamlanmaOrani.setText( "Proje %"+tamamlanmaYuzdesi +" Tamamlandı.");
                    System.out.println("yapilacakSayisi 2 :"+ yapilacakSayisi);
                    progresTamamlanma.setProgress(tamamlanmaYuzdesi);
                    projeTamamlanmaOraniTextView2.setText("%"+String.valueOf(tamamlanmaYuzdesi));

                }else{
                    tvYapilacaklarOnaySayisi.setText("Henüz yapılacak listesi oluşturulmadı.");
                    tvProjeTamamlanmaOrani.setText("Proje %0 Tamamlandı.");
                    projeTamamlanmaOraniTextView2.setText("%0");
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        System.out.println("gelenDeger : "+ yapilacakSayisi);





        // yapilacakDetayImageView.setImageResource(R.drawable.ok);



        return satirView;

    }



}
