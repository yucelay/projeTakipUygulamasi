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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class personelListesiAdaptor extends BaseAdapter {
    private FirebaseDatabase db;
    private LayoutInflater layoutInflater;
    private List<personelDb> list;
    private Activity activity;
    private personelDb personeller;
    public personelListesiAdaptor(Activity activity, List<personelDb> mList){
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
        satirView=layoutInflater.inflate(R.layout.personel_listesi_satir, null);
        final TextView tvP_Adi = satirView.findViewById(R.id.personelAdiTextView);
        TextView tvP_mail = satirView.findViewById(R.id.personelEmailTextView);
        Button personelDuzenleButon = satirView.findViewById(R.id.personelDuzenleButon);
        Button personelSilButon = satirView.findViewById(R.id.personelSilButon);

        personeller=list.get(position);
        tvP_Adi.setText(personeller.getP_adi_soyadi().toString());
        tvP_mail.setText(personeller.getP_mail().toString());
        final String dbKey = personeller.getP_key();

        personelSilButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference refNew = db.getReference("personel");
                refNew.child(dbKey).removeValue();
                Toast.makeText(personelListesiAdaptor.this.activity,tvP_Adi.getText().toString()+" adli kişi silindi.",Toast.LENGTH_SHORT).show();
            }
        });




        return satirView;
    }


}