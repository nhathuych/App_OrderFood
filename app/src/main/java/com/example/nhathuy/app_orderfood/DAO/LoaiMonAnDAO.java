package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.LoaiMonAn;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnDAO {
    SQLiteDatabase database;
    CreateDatabase myDB;

    public LoaiMonAnDAO(Context context) {
        myDB = new CreateDatabase(context);
        database = myDB.getWritableDatabase();
    }

    public boolean themLoaiMonAn(String loaiMonAn) {
        ContentValues values = new ContentValues();
        values.put(myDB.LM_TenLoai, loaiMonAn);

        long ktra = database.insert(myDB.TB_LoaiMonAn, null, values);
        if(ktra != 0) {
            return true;
        }

        return false;
    }

    public List<LoaiMonAn> layDanhSachLoaiMonAn() {
        List<LoaiMonAn> dsLoaiMon = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + myDB.TB_LoaiMonAn, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dsLoaiMon.add(new LoaiMonAn(cursor.getInt(cursor.getColumnIndex(myDB.LM_MaLoai)), cursor.getString(cursor.getColumnIndex(myDB.LM_TenLoai))));
            cursor.moveToNext();
        }

        return dsLoaiMon;
    }

    public String layHinhLoaiMonAn(int maLoai) {
        String hinhMonAn = "";
        String sQuery = String.format("select * from %s where %s = '%s' and %s != '' order by %s limit 1",
                myDB.TB_MonAn, myDB.MA_MaLoai, maLoai, myDB.MA_HinhMonAn, myDB.MA_MaMon);
        Cursor cursor = database.rawQuery(sQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            hinhMonAn = cursor.getString(cursor.getColumnIndex(myDB.MA_HinhMonAn));
            cursor.moveToNext();
        }

        return hinhMonAn;
    }
}
