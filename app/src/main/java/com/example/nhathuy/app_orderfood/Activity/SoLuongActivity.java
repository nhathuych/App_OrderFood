package com.example.nhathuy.app_orderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.DAO.GoiMonDAO;
import com.example.nhathuy.app_orderfood.DTO.ChiTietGoiMon;
import com.example.nhathuy.app_orderfood.R;

public class SoLuongActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtSoLuong;
    Button btnThemSoLuong;

    int maban, mamon;
    GoiMonDAO goiMonDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsoluong);

        edtSoLuong = (EditText) findViewById(R.id.edtSoLuongMonAn);
        btnThemSoLuong = (Button) findViewById(R.id.btnThemSoLuong);

        goiMonDAO = new GoiMonDAO(this);
        Intent intent = getIntent();
        maban = intent.getIntExtra("maban", 0);
        mamon = intent.getIntExtra("mamonan", 0);

        btnThemSoLuong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int magoimon = goiMonDAO.maGoiMon(maban, "false");
        boolean ktra = goiMonDAO.kiemtraMonAn(magoimon, mamon);

        //Log.d("--------", "mã món: " + mamon);

        if (ktra) {
            // cập nhật món ăn đã tồn tại
            int soLuongCu = goiMonDAO.soLuongMonAnTheoMaGoiMon(magoimon, mamon);
            int soLuongMoi = Integer.parseInt(edtSoLuong.getText().toString());

            boolean res = goiMonDAO.capNhatSoLuong(new ChiTietGoiMon(mamon, magoimon, soLuongCu + soLuongMoi));
            if (res) {
                Toast.makeText(this, R.string.themthanhcong, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.themthatbai, Toast.LENGTH_SHORT).show();
            }
        } else {
            // thêm mới món ăn
            boolean res = goiMonDAO.themChiTietGoiMon(new ChiTietGoiMon(mamon, magoimon, Integer.parseInt(edtSoLuong.getText().toString())));
            if (res) {
                Toast.makeText(this, R.string.themthanhcong, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.themthatbai, Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}
