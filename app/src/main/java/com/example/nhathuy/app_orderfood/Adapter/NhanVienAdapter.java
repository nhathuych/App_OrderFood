package com.example.nhathuy.app_orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.DTO.NhanVien;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<NhanVien> dsNV;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> dsNV) {
        this.context = context;
        this.layout = layout;
        this.dsNV = dsNV;
    }

    @Override
    public int getCount() {
        return dsNV.size();
    }

    @Override
    public Object getItem(int position) {
        return dsNV.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsNV.get(position).getMaNV();
    }

    class ViewHolder {
        TextView txtTenNV, txtCMND;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.txtTenNV = (TextView) convertView.findViewById(R.id.txtTenNV);
            holder.txtCMND = (TextView) convertView.findViewById(R.id.txtCMND);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTenNV.setText(dsNV.get(position).getTenDN());
        holder.txtCMND.setText("CMND: " + dsNV.get(position).getCMND());

        return convertView;
    }
}
