package com.example.yucel.projetakipuygulamasi;

public class personelDb {

    private String p_adi_soyadi,p_sifre,p_mail,p_key;
    private int projeID;


    public personelDb(){

    }

    public personelDb(int projeID,String p_adi_soyadi,String p_sifre,String p_mail,String p_key){

        this.projeID = projeID;
        this.p_adi_soyadi = p_adi_soyadi;
        this.p_sifre = p_sifre;
        this.p_mail = p_mail;
        this.p_key = p_key;

    }

    public int getProjeID(){
        return projeID;
    }

    public void setProjeID(int projeID){
        this.projeID=projeID;
    }



    public String getP_adi_soyadi(){
        return p_adi_soyadi;
    }

    public void setP_adi_soyadi(String p_adi_soyadi){
        this.p_adi_soyadi=p_adi_soyadi;
    }


    public String getP_sifre(){
        return p_sifre;
    }

    public void setP_sifre(String p_sifre){
        this.p_sifre=p_sifre;
    }


    public String getP_mail(){
        return p_mail;
    }

    public void setP_mail(String p_mail){
        this.p_mail = p_mail;
    }

    public String getP_key(){
        return p_key;
    }

    public void setP_key(String p_key){
        this.p_key = p_key;
    }

}
