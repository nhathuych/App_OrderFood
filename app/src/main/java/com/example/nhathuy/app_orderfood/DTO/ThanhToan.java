package com.example.nhathuy.app_orderfood.DTO;

public class ThanhToan {
    private String tenMonAn;
    private int soLuong, giaTien;

    public ThanhToan() {
    }

    public ThanhToan(String tenMonAn, int soLuong, int giaTien) {
        this.tenMonAn = tenMonAn;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }
    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
