package com.example.yucel.projetakipuygulamasi;

public class gorevlerDb {

    int projeId;
    String gorev_tarihi,gorev_basligi,gorev_aciklamasi;

    public gorevlerDb() {
    }

    public gorevlerDb(int projeId, String gorev_tarihi, String gorev_basligi, String gorev_aciklamasi) {
        this.projeId = projeId;
        this.gorev_tarihi = gorev_tarihi;
        this.gorev_basligi = gorev_basligi;
        this.gorev_aciklamasi = gorev_aciklamasi;
    }


    public int getProjeId() {
        return projeId;
    }

    public void setProjeId(int projeId) {
        this.projeId = projeId;
    }

    public String getGorev_tarihi() {
        return gorev_tarihi;
    }

    public void setGorev_tarihi(String gorev_tarihi) {
        this.gorev_tarihi = gorev_tarihi;
    }

    public String getGorev_basligi() {
        return gorev_basligi;
    }

    public void setGorev_basligi(String gorev_basligi) {
        this.gorev_basligi = gorev_basligi;
    }

    public String getGorev_aciklamasi() {
        return gorev_aciklamasi;
    }

    public void setGorev_aciklamasi(String gorev_aciklamasi) {
        this.gorev_aciklamasi = gorev_aciklamasi;
    }
}
