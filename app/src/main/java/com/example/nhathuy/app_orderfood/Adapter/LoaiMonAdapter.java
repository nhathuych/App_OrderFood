package com.example.nhathuy.app_orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.DTO.LoaiMonAn;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class LoaiMonAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAn> dsLoaiMon;

    public LoaiMonAdapter(Context context, int layout, List<LoaiMonAn> dsLoaiMon) {
        this.context = context;
        this.layout = layout;
        this.dsLoaiMon = dsLoaiMon;
    }

    @Override
    public int getCount() {
        return dsLoaiMon.size();
    }

    @Override
    public Object getItem(int position) {
        return dsLoaiMon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsLoaiMon.get(position).getMaLoai();
    }

    class ViewHolder {
        TextView txtTenLoai;
    }

//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
//            holder = new ViewHolder();
//            holder.txtTenLoai = (TextView) convertView.findViewById(R.id.txtTenLoai);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        LoaiMonAn loaiMonAn = dsLoaiMon.get(position);
//        holder.txtTenLoai.setText(loaiMonAn.getTenLoai());
//        holder.txtTenLoai.setTag(loaiMonAn.getMaLoai());
//
//        return convertView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.txtTenLoai = (TextView) convertView.findViewById(R.id.txtTenLoai);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LoaiMonAn loaiMonAn = dsLoaiMon.get(position);
        holder.txtTenLoai.setText(loaiMonAn.getTenLoai());
        holder.txtTenLoai.setTag(loaiMonAn.getMaLoai());

        return convertView;
    }
}
