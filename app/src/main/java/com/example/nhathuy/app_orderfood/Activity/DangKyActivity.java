package com.example.nhathuy.app_orderfood.Activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.DAO.NhanVienDAO;
import com.example.nhathuy.app_orderfood.DAO.PhanQuyenDAO;
import com.example.nhathuy.app_orderfood.DTO.NhanVien;
import com.example.nhathuy.app_orderfood.DTO.PhanQuyen;
import com.example.nhathuy.app_orderfood.Fragment.MyDatePickerFragment;
import com.example.nhathuy.app_orderfood.R;

import java.util.ArrayList;
import java.util.List;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    TextView txtTieuDe;
    EditText edtTenDangNhapDK, edtMatKhauDK, edtNgaySinh, edtCMND;
    RadioGroup rdgGioiTinh;
    RadioButton rdbNam, rdbNu;
    Button btnXacNhan, btnThoat;
    Spinner spnPhanQuyen;

    NhanVienDAO nhanVienDAO;
    PhanQuyenDAO phanQuyenDAO;
    NhanVien nv = null;
    int landautien = 0;
    List<PhanQuyen> dsQuyen;
    List<String> dsTenQuyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        initView();
        edtNgaySinh.setOnFocusChangeListener(this);
        btnXacNhan.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

        nhanVienDAO = new NhanVienDAO(this);
        phanQuyenDAO = new PhanQuyenDAO(this);

        dsQuyen = phanQuyenDAO.danhSachQuyen();
        dsTenQuyen = new ArrayList<>();
        for (PhanQuyen quyen : dsQuyen) {
            dsTenQuyen.add(quyen.getTenQuyen());
        }

        landautien = getIntent().getIntExtra("landautien", 0);
        if (landautien == 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dsTenQuyen);
            spnPhanQuyen.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            spnPhanQuyen.setVisibility(View.GONE);
        }

        // xài lại form DangKyActivity để sửa thông tin nhân viên
        nv = (NhanVien) getIntent().getSerializableExtra("nhanvien");
        if (nv != null) {
            txtTieuDe.setText(R.string.capnhat);
            btnXacNhan.setText(R.string.capnhat);
            edtTenDangNhapDK.setText(nv.getTenDN());
            edtMatKhauDK.setText(nv.getMatKhau());
            edtNgaySinh.setText(nv.getNgaySinh());
            edtCMND.setText(nv.getCMND() + "");
            if (nv.getGioiTinh().equals(rdbNam.getText().toString())) {
                rdbNam.setChecked(true);
            } else {
                rdbNu.setChecked(true);
            }
        }
    }

    private void initView() {
        txtTieuDe = (TextView) findViewById(R.id.txtTieuDe);
        edtTenDangNhapDK = (EditText) findViewById(R.id.edtTenDangNhapDK);
        edtMatKhauDK = (EditText) findViewById(R.id.edtMatKhauDK);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinhDK);
        edtCMND = (EditText) findViewById(R.id.edtCmndDK);
        rdgGioiTinh = (RadioGroup) findViewById(R.id.rdgGioiTinh);
        btnXacNhan = (Button) findViewById(R.id.btnXacNhanDK);
        btnThoat = (Button) findViewById(R.id.btnThoatDK);
        rdbNam = (RadioButton) findViewById(R.id.rdbNam);
        rdbNu = (RadioButton) findViewById(R.id.rdbNu);
        spnPhanQuyen = (Spinner) findViewById(R.id.spnPhanQuyen);
    }

    @Override
    public void onClick(View v) {
        String tenDangNhap = edtTenDangNhapDK.getText().toString();
        String matKhau = edtMatKhauDK.getText().toString();
        String gioiTinh = rdgGioiTinh.getCheckedRadioButtonId() == R.id.rdbNam ? "Nam" : "Nữ";
        String ngaySinh = edtNgaySinh.getText().toString();
        // nếu TextBox rỗng thì ko ép kiểu dc
        int soCMND = 0;
        if (!edtCMND.getText().toString().equals("")) {
            soCMND = Integer.parseInt(edtCMND.getText().toString());
        }

        switch (v.getId()) {
            case R.id.btnXacNhanDK:
                if (nv != null) {
                    // mã nhân viên ko cần sửa nhưng object NhanVien truyền vào phải có mã nhân viên để truy vấn
                    boolean res = nhanVienDAO.suaNhanVien(new NhanVien(nv.getMaNV(), soCMND, tenDangNhap, matKhau, gioiTinh, ngaySinh));
                    Snackbar.make(btnXacNhan, res ? R.string.suathanhcong : R.string.loi, Snackbar.LENGTH_SHORT).show();
                } else {
                    // đăng ký thêm nhân viên
                    if (tenDangNhap.equals("") || tenDangNhap == null) {
                        Snackbar.make(btnXacNhan, R.string.nhaplaitaikhoan, Snackbar.LENGTH_SHORT).show();
                    } else if (matKhau.equals("") || matKhau == null) {
                        Snackbar.make(btnXacNhan, R.string.nhaplaimatkhau, Snackbar.LENGTH_SHORT).show();
                    } else {
                        int maQuyen = -1;
                        if (landautien != 0) {
                            maQuyen = 1;
                        } else {
                            maQuyen = dsQuyen.get(spnPhanQuyen.getSelectedItemPosition()).getMaQuyen();
                        }
                        long kTra = nhanVienDAO.themNhanVien(new NhanVien(soCMND, tenDangNhap, matKhau, gioiTinh, ngaySinh, maQuyen));

                        Snackbar.make(btnXacNhan, kTra != 0 ? R.string.dangkythanhcong : R.string.dangkythatbai, Snackbar.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btnThoatDK:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (edtNgaySinh.hasFocus()) {
            MyDatePickerFragment datePickerFragment = new MyDatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "Ngày sinh");
        }
    }
}
