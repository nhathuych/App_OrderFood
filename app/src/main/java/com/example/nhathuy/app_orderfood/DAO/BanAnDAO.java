package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.BanAn;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    CreateDatabase myDB;
    SQLiteDatabase database;

    public BanAnDAO(Context context) {
        myDB = new CreateDatabase(context);
        database = myDB.getWritableDatabase();
    }

    public boolean themBanAn(String tenban) {
        ContentValues values = new ContentValues();
        values.put(myDB.BA_TenBan, tenban);
        values.put(myDB.BA_TinhTrang, "false");

        long ktra = database.insert(myDB.TB_BanAn, null, values);
        if(ktra != 0) {
            return true;
        }
        return false;
    }

    public List<BanAn> layDanhSachBanAn() {
        List<BanAn> dsBA = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + myDB.TB_BanAn, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            dsBA.add(new BanAn(cursor.getInt(cursor.getColumnIndex(myDB.BA_MaBan)), cursor.getString(cursor.getColumnIndex(myDB.BA_TenBan))));
            cursor.moveToNext();
        }

        return dsBA;
    }

    public String tinhTrangBan(int maban) {
        String tinhTrang = "";
        Cursor cursor = database.rawQuery(String.format("select * from %s where %s = %s", myDB.TB_BanAn, myDB.BA_MaBan, maban), null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tinhTrang = cursor.getString(cursor.getColumnIndex(myDB.BA_TinhTrang));
            cursor.moveToNext();
        }

        return tinhTrang;
    }
    
    public boolean capNhatTinhTrangBan(int maban, String tinhTrang) {
        ContentValues values = new ContentValues();
        values.put(myDB.BA_TinhTrang, tinhTrang);

        int res = database.update(myDB.TB_BanAn, values, myDB.BA_MaBan + " = " + maban, null);

        return res != 0;
    }

    public boolean xoaBanAn(int maban) {
        return database.delete(myDB.TB_BanAn, myDB.BA_MaBan + " = " + maban, null) != 0;
    }

    public boolean capNhatTenBan(int maban, String tenban) {
        ContentValues values = new ContentValues();
        values.put(myDB.BA_TenBan, tenban);
        int res = database.update(myDB.TB_BanAn, values, myDB.BA_MaBan + " = " + maban, null);

        return res != 0;
    }
}
