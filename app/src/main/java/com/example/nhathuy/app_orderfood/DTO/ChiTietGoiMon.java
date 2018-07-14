package com.example.nhathuy.app_orderfood.DTO;

public class ChiTietGoiMon {
    private int maMoAn, maGoiMon, soLuong;

    public ChiTietGoiMon() {
    }

    public ChiTietGoiMon(int maMoAn, int maGoiMon, int soLuong) {
        this.maMoAn = maMoAn;
        this.maGoiMon = maGoiMon;
        this.soLuong = soLuong;
    }

    public int getMaMoAn() {
        return maMoAn;
    }
    public void setMaMoAn(int maMoAn) {
        this.maMoAn = maMoAn;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }
    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
