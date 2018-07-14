package com.example.nhathuy.app_orderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.Adapter.LoaiMonAdapter;
import com.example.nhathuy.app_orderfood.DAO.LoaiMonAnDAO;
import com.example.nhathuy.app_orderfood.DAO.MonAnDAO;
import com.example.nhathuy.app_orderfood.DTO.LoaiMonAn;
import com.example.nhathuy.app_orderfood.DTO.MonAn;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTenMonAn, edtGiaTien;
    ImageView imgHinhThucDon;
    ImageButton imgThemLoaiThucDon;
    Spinner spnLoaiMonAn;
    Button btnXacNhanThemMonAn, btnThoatThemMonAn;

    LoaiMonAnDAO loaiMonAnDAO;
    MonAnDAO monAnDAO;
    List<LoaiMonAn> dsLoaiMon;
    LoaiMonAdapter adapter;

    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH = 123;
    String duongDanHinh = String.valueOf(R.drawable.logodangnhap);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        initView();

        imgHinhThucDon.setOnClickListener(this);
        imgThemLoaiThucDon.setOnClickListener(this);
        btnXacNhanThemMonAn.setOnClickListener(this);
        btnThoatThemMonAn.setOnClickListener(this);
        spnLoaiMonAn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO = new MonAnDAO(this);

        layDuLieuSpinner();
    }

    private void initView() {
        edtTenMonAn = (EditText) findViewById(R.id.edtTenMonAn);
        edtGiaTien = (EditText) findViewById(R.id.edtGiaTien);
        imgHinhThucDon = (ImageView) findViewById(R.id.imgHinhThucDon);
        imgThemLoaiThucDon = (ImageButton) findViewById(R.id.imgThemLoaiThucDon);
        spnLoaiMonAn = (Spinner) findViewById(R.id.spnLoaiMonAn);
        btnXacNhanThemMonAn = (Button) findViewById(R.id.btnXacNhanThemMonAn);
        btnThoatThemMonAn = (Button) findViewById(R.id.btnThoatThemMonAn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgThemLoaiThucDon:
                startActivityForResult(new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class), REQUEST_CODE_THEMLOAITHUCDON);
                break;
            case R.id.imgHinhThucDon:
                Intent intentMoHinh = new Intent();
                intentMoHinh.setType("image/*");
                intentMoHinh.setAction(Intent.ACTION_GET_CONTENT);
                // cái này trả về đường dẫn của tấm hình
                startActivityForResult(intentMoHinh.createChooser(intentMoHinh, "Chọn hình thực đơn"), REQUEST_CODE_MOHINH);
                break;
            case R.id.btnXacNhanThemMonAn:
                int maLoai = dsLoaiMon.get(spnLoaiMonAn.getSelectedItemPosition()).getMaLoai();
                String tenMonAn = edtTenMonAn.getText().toString();
                String giaTien = edtGiaTien.getText().toString();

                if (!tenMonAn.trim().isEmpty() || !giaTien.trim().isEmpty()) {
                    boolean kTra = monAnDAO.themMonAn(new MonAn(maLoai, tenMonAn, giaTien, duongDanHinh));
                    if(kTra) {
                        edtTenMonAn.setText("");
                        edtGiaTien.setText("");
                        imgHinhThucDon.setImageResource(R.drawable.logodangnhap);
                        edtTenMonAn.requestFocus();
                        Snackbar.make(btnXacNhanThemMonAn, R.string.themthanhcong, Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(btnXacNhanThemMonAn, R.string.themthatbai, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(btnXacNhanThemMonAn, R.string.loithemmoian, Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnThoatThemMonAn:
                finish();
                break;
            default:
                break;
        }
    }

    private void layDuLieuSpinner() {
        dsLoaiMon = loaiMonAnDAO.layDanhSachLoaiMonAn();
        adapter = new LoaiMonAdapter(this, R.layout.custom_spinner_loaithucdon, dsLoaiMon);
        spnLoaiMonAn.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_THEMLOAITHUCDON && resultCode == RESULT_OK) {
            boolean ktra = data.getBooleanExtra("kiemtraloaithucdon", false);
            if (ktra) {
                Toast.makeText(this, getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                layDuLieuSpinner();
            } else {
                Toast.makeText(this, getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_MOHINH && resultCode == RESULT_OK && data != null) {
//            // lấy đường dẫn của tấm hình
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
//                imgHinhThucDon.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            duongDanHinh = data.getData().toString();
            imgHinhThucDon.setImageURI(data.getData());
        }
    }
}
