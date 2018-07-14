package com.example.nhathuy.app_orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.DTO.ThanhToan;
import com.example.nhathuy.app_orderfood.R;

import java.text.NumberFormat;
import java.util.List;

public class ThanhToanAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ThanhToan> dsThanhToan;

    public ThanhToanAdapter(Context context, int layout, List<ThanhToan> dsThanhToan) {
        this.context = context;
        this.layout = layout;
        this.dsThanhToan = dsThanhToan;
    }

    @Override
    public int getCount() {
        return dsThanhToan.size();
    }

    @Override
    public Object getItem(int position) {
        return dsThanhToan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView txtTenMonAn, txtSoLuong, txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.txtTenMonAn = (TextView) convertView.findViewById(R.id.txtTenMonAn_ThanhToan);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.txtSoLuong_ThanhToan);
            holder.txtGiaTien = (TextView) convertView.findViewById(R.id.txtGiaTien_ThanhToan);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTenMonAn.setText(dsThanhToan.get(position).getTenMonAn());
        holder.txtSoLuong.setText(dsThanhToan.get(position).getSoLuong() + "");
        holder.txtGiaTien.setText(NumberFormat.getInstance().format(dsThanhToan.get(position).getGiaTien()));

        return convertView;
    }
}
