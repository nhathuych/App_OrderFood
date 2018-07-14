package com.example.nhathuy.app_orderfood.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.Adapter.ThanhToanAdapter;
import com.example.nhathuy.app_orderfood.DAO.BanAnDAO;
import com.example.nhathuy.app_orderfood.DAO.GoiMonDAO;
import com.example.nhathuy.app_orderfood.DTO.ThanhToan;
import com.example.nhathuy.app_orderfood.R;

import java.text.NumberFormat;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {
    GridView grvThanhToan;
    TextView txtTongTien;
    Button btnThanhToan, btnThoatThanhToan;
    ThanhToanAdapter adapter;

    GoiMonDAO goiMonDAO;
    BanAnDAO banAnDAO;
    List<ThanhToan> dsHoaDon;

    int maban;
    long tongTien = 0;
    boolean isPaid = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        grvThanhToan = (GridView) findViewById(R.id.grvThanhToan);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        btnThoatThanhToan = (Button) findViewById(R.id.btnThoatThanhToan);

        goiMonDAO = new GoiMonDAO(this);
        banAnDAO = new BanAnDAO(this);

        maban = getIntent().getIntExtra("maban", -1);
        if (maban != -1) {
            resumeView();

            // tính tiền
            for (ThanhToan thanhToan : dsHoaDon) {
                tongTien += thanhToan.getSoLuong() * thanhToan.getGiaTien();
            }

            txtTongTien.append(" " + NumberFormat.getInstance().format(tongTien) + " vnd");
        }

        btnThanhToan.setOnClickListener(this);
        btnThoatThanhToan.setOnClickListener(this);
    }

    private void resumeView() {
        dsHoaDon = goiMonDAO.danhSachMonAn(goiMonDAO.maGoiMon(maban, "false"));
        adapter = new ThanhToanAdapter(this, R.layout.custom_layout_thanhtoan, dsHoaDon);
        grvThanhToan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThanhToan:
                boolean ktraTinhTrang = banAnDAO.capNhatTinhTrangBan(maban, "false");
                boolean ktraTrangThai = goiMonDAO.capNhatTrangThai(maban, "true");
                if (ktraTinhTrang && ktraTrangThai && isPaid == false) {
                    isPaid = true;
                    Toast.makeText(this, R.string.thanhtoanthanhcong, Toast.LENGTH_SHORT).show();
                    //resumeView();
                    break;
                }
                Toast.makeText(this, R.string.loithanhtoan, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnThoatThanhToan:
                finish();
                break;
        }
    }
}
