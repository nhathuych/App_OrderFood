package com.example.nhathuy.app_orderfood.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.nhathuy.app_orderfood.R;

import java.util.Calendar;

public class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), MyDatePickerFragment.this, nam, thang, ngay);
    }

    // bắt sự kiện người dùng set lại ngày tháng trên DatePickerFragment
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText edtNgaySinh = (EditText) getActivity().findViewById(R.id.edtNgaySinhDK);
        EditText edtCmnd = (EditText) getActivity().findViewById(R.id.edtCmndDK);

        edtNgaySinh.setText(dayOfMonth + " - " + (month + 1) + " - " + year);   // ko hiểu sao nó trừ mất 1 tháng
        edtCmnd.requestFocus();
    }
}
