package com.example.yucel.projetakipuygulamasi;

public class yapilacaklarDb {
    int onay,projeId;
    String yapilacakAdi;


    public yapilacaklarDb(){

    }

    public yapilacaklarDb(int onay, int projeId, String yapilacakAdi) {
        this.onay = onay;
        this.projeId = projeId;
        this.yapilacakAdi = yapilacakAdi;
    }

    public int getOnay() {
        return onay;
    }

    public void setOnay(int onay) {
        this.onay = onay;
    }

    public int getProjeId() {
        return projeId;
    }

    public void setProjeId(int projeId) {
        this.projeId = projeId;
    }

    public String getYapilacakAdi() {
        return yapilacakAdi;
    }

    public void setYapilacakAdi(String yapilacakAdi) {
        this.yapilacakAdi = yapilacakAdi;
    }
}
