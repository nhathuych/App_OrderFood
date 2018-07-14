package com.example.nhathuy.app_orderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nhathuy.app_orderfood.DAO.BanAnDAO;
import com.example.nhathuy.app_orderfood.R;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtThemBanAn;
    Button btnThemBanAn;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edtThemBanAn = (EditText) findViewById(R.id.edtThemBanAn);
        btnThemBanAn = (Button) findViewById(R.id.btnThemBanAn);

        banAnDAO = new BanAnDAO(this);
        btnThemBanAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String tenBanAn = edtThemBanAn.getText().toString();
        if(!tenBanAn.equals("")) {
            boolean ktra = banAnDAO.themBanAn(tenBanAn);
            Intent intent = new Intent();
            intent.putExtra("ketQuaThem", ktra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
