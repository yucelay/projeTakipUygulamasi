package com.example.yucel.projetakipuygulamasi;

import android.app.Activity;
import android.content.Context;
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

public class testEdilmisProjelerAdaptor extends BaseAdapter {

    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<testEdilmisProjelerDb> list;
    private Activity activity;
    private Button projeyiSilButon;
    public testEdilmisProjelerAdaptor(Activity activity, List<testEdilmisProjelerDb> mList){
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
        satirView=layoutInflater.inflate(R.layout.test_edilmis_projeler_satir, null);
        TextView tvProjeID = satirView.findViewById(R.id.projeIDTextView);
        final TextView tvProjeAdi = satirView.findViewById(R.id.projeAdiTextView);
        final Button arsiveEkleButon = satirView.findViewById(R.id.arsiveEkleButon);
        final testEdilmisProjelerDb projeler=list.get(position);
        tvProjeID.setText("#"+String.valueOf(projeler.getProjeID()));



        final DatabaseReference projeleriGetir=db.getReference("projeler");
        projeleriGetir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot gelenler:dataSnapshot.getChildren()) {
                    int projeID = gelenler.getValue(projelerDb.class).getProjeID();
                    String projeAdi = gelenler.getValue(projelerDb.class).getProjeAdi();
                    if (projeler.getProjeID() == projeID) {
                        tvProjeAdi.setText(projeAdi);
                    }
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        arsiveEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference refId = db.getReference("arsiv").child(String.valueOf(projeler.getProjeID())).child("projeId");
                DatabaseReference refProjeAdi = db.getReference("arsiv").child(String.valueOf(projeler.getProjeID())).child("projeAdi");
                refId.setValue(projeler.getProjeID());
                refProjeAdi.setValue(tvProjeAdi.getText());
                arsiveEkleButon.setEnabled(false);
            }
        });
        return satirView;
    }

}
