package com.example.nhathuy.app_orderfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.DAO.NhanVienDAO;
import com.example.nhathuy.app_orderfood.R;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTaiKhoanDN, edtMatKhauDN;
    Button btnDongYDN, btnDangKyDN;

    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        initView();
        nhanVienDAO = new NhanVienDAO(this);
        AnHienButton();
    }

    private void initView() {
        edtTaiKhoanDN = (EditText) findViewById(R.id.edtTenDangNhapDN);
        edtMatKhauDN = (EditText) findViewById(R.id.edtMatKhauDN);
        btnDongYDN = (Button) findViewById(R.id.btnDongYDN);
        btnDangKyDN = (Button) findViewById(R.id.btnDangKyDN);

        btnDangKyDN.setOnClickListener(this);
        btnDongYDN.setOnClickListener(this);
    }

    private void AnHienButton() {
        if(nhanVienDAO.kiemTraNhanVien()) {
            // cho btnDangKyDN ẩn đi, btnXacNhanDN tự động thế chổ của btnDangKyDN
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        } else {
            // cho btnXacNhanDN ẩn đi, btnDangKyDN tự động thế chổ của btnDangKyDN
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnHienButton();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDongYDN:
                btnDongY();
                break;
            case R.id.btnDangKyDN:
                btnDangKy();
                break;
        }
    }

    private void btnDongY() {
        try {
            int ktra = nhanVienDAO.kiemTraDangNhap(edtTaiKhoanDN.getText().toString(), edtMatKhauDN.getText().toString());
            int maQuyen = nhanVienDAO.layQuyenNhanVien(ktra);
            if(ktra != 0) {
                // MODE_PRIVATE: chỉ có ứng dụng này đọc thôi, mấy ứng dụng khác ko thể đọc dc
                SharedPreferences sharedPreferences = getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("maquyen", maQuyen);
                editor.commit();

                Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                intent.putExtra("taikhoan", edtTaiKhoanDN.getText().toString());
                intent.putExtra("maNhanVien", ktra);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_activity_vo, R.anim.anim_activity_ra);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void btnDangKy() {
        Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
        intent.putExtra("landautien", 1);
        startActivity(intent);
    }
}
