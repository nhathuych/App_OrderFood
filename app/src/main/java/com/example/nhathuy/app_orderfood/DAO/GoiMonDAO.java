package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.ChiTietGoiMon;
import com.example.nhathuy.app_orderfood.DTO.GoiMon;
import com.example.nhathuy.app_orderfood.DTO.ThanhToan;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class GoiMonDAO {
    CreateDatabase myDB;
    SQLiteDatabase database;

    public GoiMonDAO(Context context) {
        myDB = new CreateDatabase(context);
        database = myDB.getWritableDatabase();
    }

    public long themGoiMon(GoiMon goiMon) {
        ContentValues values = new ContentValues();
        values.put(myDB.GM_MaBan, goiMon.getMaBan());
        values.put(myDB.GM_MaNV, goiMon.getMaNhanVien());
        values.put(myDB.GM_NgayGoi, goiMon.getNgayGoi());
        values.put(myDB.GM_TinhTrang, goiMon.getTinhTrang());

        return database.insert(myDB.TB_GoiMon, null, values);
    }

    public int maGoiMon(int maban, String tinhtrang) {
        int ma = 0;
        String s = String.format("select * from %s where %s = '%s' and %s = '%s'",
                myDB.TB_GoiMon, myDB.GM_MaBan, maban, myDB.GM_TinhTrang, tinhtrang);

        Cursor cursor = database.rawQuery(s, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ma = cursor.getInt(cursor.getColumnIndex(myDB.GM_MaGoiMon));
            cursor.moveToNext();
        }

        return ma;
    }

    public boolean kiemtraMonAn(int magoimon, int mamonan) {
        String s = String.format("select * from %s where %s = %s and %s = %s"
                , myDB.TB_ChiTietGoiMon, myDB.CT_MaMonAn, mamonan, myDB.CT_MaGoiMon, magoimon);

        Cursor cursor = database.rawQuery(s, null);

        return cursor.getCount() != 0 ? true : false;
    }

    public int soLuongMonAnTheoMaGoiMon(int magoimon, int mamonan) {
        int soluong = 0;
        String s = String.format("select * from %s where %s = %s and %s = %s",
                myDB.TB_ChiTietGoiMon, myDB.CT_MaMonAn, mamonan, myDB.CT_MaGoiMon, magoimon);

        Cursor cursor = database.rawQuery(s, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            soluong = cursor.getInt(cursor.getColumnIndex(myDB.CT_SoLuong));
        }

        return soluong;
    }

    public boolean capNhatSoLuong(ChiTietGoiMon chiTietGoiMon) {
        ContentValues values = new ContentValues();
        values.put(myDB.CT_SoLuong, chiTietGoiMon.getSoLuong());
        String s = myDB.CT_MaGoiMon + " = " + chiTietGoiMon.getMaGoiMon() + " and " + myDB.CT_MaMonAn + " = " + chiTietGoiMon.getMaMoAn();
        int ktra = database.update(myDB.TB_ChiTietGoiMon, values, s, null);

        return ktra != 0 ? true : false;
    }

    public boolean themChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        ContentValues values = new ContentValues();
        values.put(myDB.CT_SoLuong, chiTietGoiMon.getSoLuong());
        values.put(myDB.CT_MaGoiMon, chiTietGoiMon.getMaGoiMon());
        values.put(myDB.CT_MaMonAn, chiTietGoiMon.getMaMoAn());

        long res = database.insert(myDB.TB_ChiTietGoiMon, null, values);

        return res != 0 ? true : false;
    }

    public List<ThanhToan> danhSachMonAn(int maGoiMon) {
        List<ThanhToan> list = new ArrayList<>();
        String sql = String.format("select * from %s ct, %s ma where ct.%s = ma.%s and %s = %s",
                myDB.TB_ChiTietGoiMon, myDB.TB_MonAn, myDB.CT_MaMonAn, myDB.MA_MaMon, myDB.CT_MaGoiMon, maGoiMon);
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new ThanhToan(
                    cursor.getString(cursor.getColumnIndex(myDB.MA_TenMonAn)),
                    cursor.getInt(cursor.getColumnIndex(myDB.CT_SoLuong)),
                    cursor.getInt(cursor.getColumnIndex(myDB.MA_GiaTien))
            ));
            cursor.moveToNext();
        }

        return list;
    }

    // thanh toán xong thì cập nhật lại trạng thái bàn ăn
    public boolean capNhatTrangThai(int maban, String tinhtrang) {
        ContentValues values = new ContentValues();
        values.put(myDB.GM_MaBan, maban);
        int ktra = database.update(myDB.TB_GoiMon, values, null, null);

        return ktra != 0 ? true : false;
    }
}
