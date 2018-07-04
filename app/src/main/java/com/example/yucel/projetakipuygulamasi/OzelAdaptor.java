package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OzelAdaptor extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<projelerDb> list;
    private Activity activity;
    private FirebaseDatabase db;
    private Context context;
    private String data2 = "";
    private int personelSayisi;


    public OzelAdaptor(Activity activity, List<projelerDb> mList) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = mList;
        this.activity = activity;
        context = activity;
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
        db = FirebaseDatabase.getInstance();
        View satirView;
        satirView = layoutInflater.inflate(R.layout.projeler_satir, null);
        TextView tvProjeID = satirView.findViewById(R.id.projeIDTextView);
        final TextView tvProjeAdi = satirView.findViewById(R.id.projeAdiTextView);
        final TextView tvPersonelSayisi = satirView.findViewById(R.id.projePersonelSayisiTextView);
        Button personelEkleButon = satirView.findViewById(R.id.personelEkleButon);
        Button personelBilgileriniGosterButon = satirView.findViewById(R.id.personelBilgileriButon);


        final projelerDb projeler = list.get(position);
        tvProjeID.setText("#" + String.valueOf(projeler.getProjeID()));
        tvProjeAdi.setText(projeler.getProjeAdi().toString());


        DatabaseReference ref = db.getReference("personel");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personelSayisi = 0;
                for (DataSnapshot personeller : dataSnapshot.getChildren()) {
                    data2 = personeller.child("projeID").getValue().toString();
                    if (projeler.getProjeID() == Integer.valueOf(data2)) {
                        personelSayisi++;
                    }
                }
                tvPersonelSayisi.setText("Projede "+String.valueOf(personelSayisi)+" kişi çalışıyor");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //tvPersonelSayisi.setText(String.valueOf(personelSayisi));

        //tvPersonelSayisi.setText(String.valueOf(projeler.getProjedekiPersonelSayisi()) + " kişi");

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
