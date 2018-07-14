package com.example.nhathuy.app_orderfood.DTO;

public class LoaiMonAn {
    private int maLoai;
    private String tenLoai, hinhMonAn;

    public LoaiMonAn(String hinhMonAn) {
        this.hinhMonAn = hinhMonAn;
    }

    public LoaiMonAn(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhMonAn() {
        return hinhMonAn;
    }
    public void setHinhMonAn(String hinhMonAn) {
        this.hinhMonAn = hinhMonAn;
    }
}
