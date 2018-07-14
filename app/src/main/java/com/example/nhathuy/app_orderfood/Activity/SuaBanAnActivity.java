package com.example.nhathuy.app_orderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.DAO.BanAnDAO;
import com.example.nhathuy.app_orderfood.R;

public class SuaBanAnActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTenBan;
    Button btnXacNhanSua;

    BanAnDAO banAnDAO;
    int maban;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suabanan);

        edtTenBan = (EditText) findViewById(R.id.edtBanAn_Sua);
        btnXacNhanSua = (Button) findViewById(R.id.btnXacNhan_Sua);

        banAnDAO = new BanAnDAO(this);

        maban = getIntent().getIntExtra("maban", 0);

        btnXacNhanSua.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String tenban = edtTenBan.getText().toString().trim();
        if (!tenban.equals("")) {
            boolean ktra = banAnDAO.capNhatTenBan(maban, tenban);
            Intent intent = new Intent();
            intent.putExtra("kiemtra", ktra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, R.string.vuilongnhapdulieu, Toast.LENGTH_SHORT).show();
        }
    }
}
