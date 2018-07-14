package com.example.nhathuy.app_orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhathuy.app_orderfood.DTO.PhanQuyen;
import com.example.nhathuy.app_orderfood.SQLiteHelper.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class PhanQuyenDAO {
    CreateDatabase myDB;
    SQLiteDatabase database;

    public PhanQuyenDAO(Context context) {
        myDB = new CreateDatabase(context);
        database = myDB.getWritableDatabase();

        if (database.rawQuery("select * from " + myDB.TB_PhanQuyen, null).getCount() == 0) {
            themQuyen("Quản lý");
            themQuyen("Nhân viên");
        }
    }

    public void themQuyen(String tenquyen) {
        ContentValues values = new ContentValues();
        values.put(myDB.PQ_TenQuyen, tenquyen);
        database.insert(myDB.TB_PhanQuyen, null, values);
    }

    public List<PhanQuyen> danhSachQuyen() {
        List<PhanQuyen> dsQuyen = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + myDB.TB_PhanQuyen, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dsQuyen.add(new PhanQuyen(
                    cursor.getInt(cursor.getColumnIndex(myDB.PQ_MaQuyen)),
                    cursor.getString(cursor.getColumnIndex(myDB.PQ_TenQuyen))
            ));

            cursor.moveToNext();
        }

        return dsQuyen;
    }
}
