package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.NhanVien;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    CreateDatabase myDB;
    SQLiteDatabase database;

    public NhanVienDAO(Context context) {
        // qua hàm Main, chỉ cần khai báo class NhanVienDAO thôi, vì nó chứa mọi hàm của class CreateDatabase rồi
        myDB = new CreateDatabase(context);
    }

    public void open() {
        database = myDB.getWritableDatabase();
    }

    public int layQuyenNhanVien(int maNhanVien) {
        open();
        Cursor cursor = database.rawQuery(String.format("select * from %s where %s = %s", myDB.TB_NhanVien, myDB.NV_MaNV, maNhanVien), null);
        cursor.moveToFirst();   // cho cursor trỏ xuống dòng đầu tiên của bảng NhanVien vì lúc đầu nó trỏ ở mấy cái tên cột
        return cursor.getInt(cursor.getColumnIndex(myDB.NV_MaQuyen));
    }

    public long themNhanVien(NhanVien sv) {
        open();
        ContentValues values = new ContentValues();
        values.put(myDB.NV_TenDN, sv.getTenDN());
        values.put(myDB.NV_MatKhau, sv.getMatKhau());
        values.put(myDB.NV_CMND, sv.getCMND());
        values.put(myDB.NV_NgaySinh, sv.getNgaySinh());
        values.put(myDB.NV_GioiTinh, sv.getGioiTinh());
        values.put(myDB.NV_MaQuyen, sv.getMaQuyen());

        return database.insert(myDB.TB_NhanVien, null, values);
    }

    public boolean suaNhanVien(NhanVien nv) {
        open();
        ContentValues values = new ContentValues();
        // mã nhân viên ko cần sửa nhưng object NhanVien truyền vào phải có mã nhân viên để truy vấn
        values.put(myDB.NV_TenDN, nv.getTenDN());
        values.put(myDB.NV_MatKhau, nv.getMatKhau());
        values.put(myDB.NV_CMND, nv.getCMND());
        values.put(myDB.NV_NgaySinh, nv.getNgaySinh());
        values.put(myDB.NV_GioiTinh, nv.getGioiTinh());

        return database.update(myDB.TB_NhanVien, values, myDB.NV_MaNV + " = " + nv.getMaNV(), null) != 0;
    }

    // kiểm tra xem trong CSDL đã có nhân viên nào chưa, nếu có rồi thì ẩn nút đăng ký
    public boolean kiemTraNhanVien() {
        open();
        Cursor cursor = database.rawQuery("select * from " + myDB.TB_NhanVien, null);
        if (cursor.getCount() == 0) {
            return false;
        }
        return true;
    }

    public int kiemTraDangNhap(String taiKhoan, String matKhau) {
        Cursor cursor = database.rawQuery(String.format("select * from %s where %s = '%s' and %s = '%s'",
                myDB.TB_NhanVien, myDB.NV_TenDN, taiKhoan, myDB.NV_MatKhau, matKhau), null);

        int maNV = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            maNV = cursor.getInt(cursor.getColumnIndex(myDB.NV_MaNV));
            cursor.moveToNext();
        }

        return maNV;
    }

    public List<NhanVien> danhSachNhanVien() {
        open();
        List<NhanVien> dsNV = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + myDB.TB_NhanVien, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dsNV.add(new NhanVien(
                    cursor.getInt(cursor.getColumnIndex(myDB.NV_MaNV)),
                    cursor.getInt(cursor.getColumnIndex(myDB.NV_CMND)),
                    cursor.getString(cursor.getColumnIndex(myDB.NV_TenDN)),
                    cursor.getString(cursor.getColumnIndex(myDB.NV_MatKhau)),
                    cursor.getString(cursor.getColumnIndex(myDB.NV_GioiTinh)),
                    cursor.getString(cursor.getColumnIndex(myDB.NV_NgaySinh))
            ));
            cursor.moveToNext();
        }

        return dsNV;
    }

    public boolean xoaNhanVien(int manNV) {
        return database.delete(myDB.TB_NhanVien, myDB.NV_MaNV + " = " + manNV, null) != 0;
    }
}
