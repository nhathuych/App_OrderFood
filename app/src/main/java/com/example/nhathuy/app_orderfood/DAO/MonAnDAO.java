package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.MonAn;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    SQLiteDatabase database;
    CreateDatabase myDB;

    public MonAnDAO(Context context) {
        myDB = new CreateDatabase(context);
        database = myDB.getWritableDatabase();
    }

    public boolean themMonAn(MonAn monAn) {
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.MA_TenMonAn, monAn.getTenMonAn());
        values.put(CreateDatabase.MA_GiaTien, monAn.getGiaTien());
        values.put(CreateDatabase.MA_MaLoai, monAn.getMaLoai());
        values.put(CreateDatabase.MA_HinhMonAn, monAn.getHinhMonAn());

        long kTra = database.insert(CreateDatabase.TB_MonAn, null, values);
        if (kTra != 0) {
            return true;
        }

        return false;
    }

    public List<MonAn> layDsMonTheoLoai(int maLoai) {
        List<MonAn> dsMon = new ArrayList<>();
        String sQuery = String.format("select * from %s where %s = '%s'",
                myDB.TB_MonAn, myDB.MA_MaLoai, maLoai);
        Cursor cursor = database.rawQuery(sQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // int maMonAn, int maLoai, String tenMonAn, String giaTien, String hinhMonAn
            dsMon.add(new MonAn(
                    cursor.getInt(cursor.getColumnIndex(myDB.MA_MaMon)),
                    cursor.getInt(cursor.getColumnIndex(myDB.MA_MaLoai)),
                    cursor.getString(cursor.getColumnIndex(myDB.MA_TenMonAn)),
                    cursor.getString(cursor.getColumnIndex(myDB.MA_GiaTien)),
                    cursor.getString(cursor.getColumnIndex(myDB.MA_HinhMonAn))
            ));
            cursor.moveToNext();
        }
        return dsMon;
    }
}
