package com.example.nhathuy.app_orderfood.DTO;

public class MonAn {
    int maMonAn, maLoai;
    String tenMonAn, giaTien, hinhMonAn;

    public MonAn() {
    }

    public MonAn(int maLoai, String tenMonAn, String giaTien, String hinhMonAn) {
        this.maLoai = maLoai;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.hinhMonAn = hinhMonAn;
    }

    public MonAn(int maMonAn, int maLoai, String tenMonAn, String giaTien, String hinhMonAn) {
        this.maMonAn = maMonAn;
        this.maLoai = maLoai;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.hinhMonAn = hinhMonAn;
    }

    public int getMaMonAn() {
        return maMonAn;
    }
    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public int getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }
    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhMonAn() {
        return hinhMonAn;
    }
    public void setHinhMonAn(String hinhMonAn) {
        this.hinhMonAn = hinhMonAn;
    }
}
