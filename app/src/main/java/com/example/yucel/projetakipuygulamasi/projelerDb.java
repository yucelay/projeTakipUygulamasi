package com.example.yucel.projetakipuygulamasi;

public class projelerDb {

    private String projeAdi,sifre,tarih;
    private int projeID,projedekiPersonelSayisi;


    public projelerDb(){

    }

    public projelerDb(int projeID,String projeAdi,String sifre,int projedekiPersonelSayisi,String tarih){

        this.projeID = projeID;
        this.projeAdi = projeAdi;
        this.sifre = sifre;
        this.projedekiPersonelSayisi = projedekiPersonelSayisi;
        this.tarih = tarih;

    }

    public int getProjeID(){
        return projeID;
    }

    public void setProjeID(int projeID){
        this.projeID=projeID;
    }



    public String getProjeAdi(){
        return projeAdi;
    }

    public void setProjeAdi(String projeAdi){
        this.projeAdi=projeAdi;
    }


    public String getSifre(){
        return sifre;
    }

    public void setSifre(String sifre){
        this.sifre=sifre;
    }


    public int getProjedekiPersonelSayisi(){
        return projedekiPersonelSayisi;
    }

    public void setProjedekiPersonelSayisi(int projedekiPersonelSayisi){
        this.projedekiPersonelSayisi=projedekiPersonelSayisi;
    }

    public String getTarih(){
        return tarih;
    }

    public void setTarih(String tarih){
        this.tarih=tarih;
    }

}
