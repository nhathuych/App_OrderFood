package com.example.nhathuy.app_orderfood.DTO;

public class GoiMon {
    private int maGoiMon, maBan, maNhanVien;
    private String tinhTrang, ngayGoi;

    public GoiMon() {
    }

    public GoiMon(int maBan, int maNhanVien, String tinhTrang, String ngayGoi) {
        this.maBan = maBan;
        this.maNhanVien = maNhanVien;
        this.tinhTrang = tinhTrang;
        this.ngayGoi = ngayGoi;
    }

    public GoiMon(int maGoiMon, int maBan, int maNhanVien, String tinhTrang, String ngayGoi) {
        this.maGoiMon = maGoiMon;
        this.maBan = maBan;
        this.maNhanVien = maNhanVien;
        this.tinhTrang = tinhTrang;
        this.ngayGoi = ngayGoi;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }
    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaBan() {
        return maBan;
    }
    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayGoi() {
        return ngayGoi;
    }
    public void setNgayGoi(String ngayGoi) {
        this.ngayGoi = ngayGoi;
    }
}
