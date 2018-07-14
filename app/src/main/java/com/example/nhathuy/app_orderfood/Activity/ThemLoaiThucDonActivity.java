package com.example.nhathuy.app_orderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.DAO.LoaiMonAnDAO;
import com.example.nhathuy.app_orderfood.R;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtThemLoaiThucDon;
    Button btnXacNhanThemLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_loai_thuc_don);

        edtThemLoaiThucDon = (EditText) findViewById(R.id.edtThemLoaiThucDon);
        btnXacNhanThemLoaiThucDon = (Button) findViewById(R.id.btnDongYThemLoaiThucDon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);

        btnXacNhanThemLoaiThucDon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnDongYThemLoaiThucDon) {
            String tenLoaiThucDon = edtThemLoaiThucDon.getText().toString();

            if (!tenLoaiThucDon.equals("")) {
                boolean ktra = loaiMonAnDAO.themLoaiMonAn(tenLoaiThucDon);
                Intent intent = new Intent();
                intent.putExtra("kiemtraloaithucdon", ktra);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, R.string.vuilongnhapdulieu, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
