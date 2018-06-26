package com.example.yucel.projetakipuygulamasi;

public class testEdilmisProjelerDb {
    int onayDurumu , projeID;


    public testEdilmisProjelerDb(int onayDurumu, int projeID) {
        this.onayDurumu = onayDurumu;
        this.projeID = projeID;

    }

    public testEdilmisProjelerDb(){

    }


    public int getOnayDurumu() {
        return onayDurumu;
    }

    public void setOnayDurumu(int onayDurumu) {
        this.onayDurumu = onayDurumu;
    }

    public int getProjeID() {
        return projeID;
    }

    public void setProjeID(int projeID) {
        this.projeID = projeID;
    }

}
