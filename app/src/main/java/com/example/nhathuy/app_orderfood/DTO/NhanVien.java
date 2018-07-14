package com.example.nhathuy.app_orderfood.DTO;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int maNV, CMND, maQuyen;
    private String tenDN, matKhau, gioiTinh, ngaySinh;

    public NhanVien() {
    }

    // cái này không có mã nhân viên và có thêm quyền nhân viên
    public NhanVien(int CMND, String tenDN, String matKhau, String gioiTinh, String ngaySinh, int maQuyen) {
        this.CMND = CMND;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.maQuyen = maQuyen;
    }

    // cái này không có mã nhân viên
    public NhanVien(int CMND, String tenDN, String matKhau, String gioiTinh, String ngaySinh) {
        this.CMND = CMND;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    // cái này có mã nhân viên
    public NhanVien(int maNV, int CMND, String tenDN, String matKhau, String gioiTinh, String ngaySinh) {
        this.maNV = maNV;
        this.CMND = CMND;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public int getMaQuyen() {
        return maQuyen;
    }
    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getMaNV() {
        return maNV;
    }
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getCMND() {
        return CMND;
    }
    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public String getTenDN() {
        return tenDN;
    }
    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
