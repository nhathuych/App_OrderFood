package com.example.nhathuy.app_orderfood.DTO;

public class BanAn {
    private int maBan;
    private String tenBan;
    private boolean duocChon;

    public BanAn(int maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }

    public int getMaBan() {
        return maBan;
    }
    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }
    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public boolean isDuocChon() {
        return duocChon;
    }
    public void setDuocChon(boolean duocChon) {
        this.duocChon = duocChon;
    }
}
