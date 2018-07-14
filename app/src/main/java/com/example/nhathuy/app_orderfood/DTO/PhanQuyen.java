package com.example.nhathuy.app_orderfood.DTO;

public class PhanQuyen {
    private int maQuyen;
    private String tenQuyen;

    public PhanQuyen(int maQuyen, String tenQuyen) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
    }

    public int getMaQuyen() {
        return maQuyen;
    }
    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }
    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }
}
