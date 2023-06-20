package com.muhammet.ilkproje.repository.view;

public class VwPersonel {

    String ad;
    String adres;
    String telefon;

    public VwPersonel() {
    }

    public VwPersonel(String ad,
                      String adres,
                      String telefon) {
        this.ad = ad;
        this.adres = adres;
        this.telefon = telefon;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
