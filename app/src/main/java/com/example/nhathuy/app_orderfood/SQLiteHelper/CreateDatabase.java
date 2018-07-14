package com.example.nhathuy.app_orderfood.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {
    public static String TB_NhanVien = "NhanVien";
    public static String TB_MonAn = "MonAn";
    public static String TB_LoaiMonAn = "LoaiMonAn";
    public static String TB_BanAn = "BanAn";
    public static String TB_GoiMon = "GoiMon";
    public static String TB_ChiTietGoiMon = "ChiTietGoiMon";
    public static String TB_PhanQuyen = "PhanQuyen";

    // bảng phân quyền
    public static String PQ_MaQuyen = "MaQuyen";
    public static String PQ_TenQuyen = "TenQuyen";

    // bảng nhân viên
    public static String NV_MaNV = "MaNV";
    public static String NV_MaQuyen = "MaQuyen";
    public static String NV_TenDN = "TenDN";
    public static String NV_MatKhau = "MatKhau";
    public static String NV_GioiTinh = "GioiTinh";
    public static String NV_NgaySinh = "NgaySinh";
    public static String NV_CMND = "CMND";

    // bàng món ăn
    public static String MA_MaMon = "MaMonAn";
    public static String MA_TenMonAn = "TenMonAn";
    public static String MA_GiaTien = "GiaTien";
    public static String MA_MaLoai = "MaLoai";
    public static String MA_HinhMonAn = "HinhMonAn";

    // bảng loại món ăn
    public static String LM_MaLoai = "MaLoai";
    public static String LM_TenLoai = "TenLoai";

    // bảng bàn ăn
    public static String BA_MaBan = "MaBan";
    public static String BA_TenBan = "TenBan";
    public static String BA_TinhTrang = "TinhTrang";

    // bảng gọi món
    public static String GM_MaGoiMon = "MaGoiMon";
    public static String GM_MaNV = "MaNV";
    public static String GM_NgayGoi = "NgayGoi";
    public static String GM_TinhTrang = "TinhTrang";
    public static String GM_MaBan = "MaBan";

    // bảng chi tiết gọi món
    public static String CT_MaGoiMon = "MaGoiMon";
    public static String CT_MaMonAn = "MaMonAn";
    public static String CT_SoLuong = "SoLuong";

    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
    }

    public void open() {
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nhanvien = String.format("create table %s (%s integer primary key autoincrement, %s text, %s text, %s text, %s text, %s integer, %s integer)",
                TB_NhanVien, NV_MaNV, NV_TenDN, NV_MatKhau, NV_GioiTinh, NV_NgaySinh, NV_CMND, NV_MaQuyen);
        String banan = String.format("create table %s (%s integer primary key autoincrement, %s text, %s text)",
                TB_BanAn, BA_MaBan, BA_TenBan, BA_TinhTrang);
        String monan = String.format("create table %s (%s integer primary key autoincrement, %s text, %s integer, %s text, %s text)",
                TB_MonAn, MA_MaMon, MA_TenMonAn, MA_MaLoai, MA_GiaTien, MA_HinhMonAn);
        String loaimon = String.format("create table %s (%s integer primary key autoincrement, %s text)",
                TB_LoaiMonAn, LM_MaLoai, LM_TenLoai);
        String goimon = String.format("create table %s (%s integer primary key autoincrement, %s integer, %s integer, %s text, %s text)",
                TB_GoiMon, GM_MaGoiMon, GM_MaBan, GM_MaNV, GM_NgayGoi, GM_TinhTrang);
        String chitiet = String.format("create table %s (%s integer, %s integer, %s integer, primary key (%s, %s))",
                TB_ChiTietGoiMon, CT_MaGoiMon, CT_MaMonAn, CT_SoLuong, CT_MaGoiMon, CT_MaMonAn);
        String phanquyen = String.format("create table %s (%s integer primary key autoincrement, %s text)",
                TB_PhanQuyen, PQ_MaQuyen, PQ_TenQuyen);

        db.execSQL(nhanvien);
        db.execSQL(banan);
        db.execSQL(monan);
        db.execSQL(loaimon);
        db.execSQL(goimon);
        db.execSQL(chitiet);
        db.execSQL(phanquyen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}
