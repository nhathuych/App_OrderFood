package com.example.nhathuy.app_orderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.DTO.MonAn;
import com.example.nhathuy.app_orderfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhSachMonAnAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<MonAn> dsMonAn;

    public DanhSachMonAnAdapter(Context context, int layout, List<MonAn> dsMonAn) {
        this.context = context;
        this.layout = layout;
        this.dsMonAn = dsMonAn;
    }

    @Override
    public int getCount() {
        return dsMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsMonAn.get(position).getMaMonAn();
    }

    class ViewHolder {
        ImageView imgMonAn;
        TextView txtTenMonAn, txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);

            holder.imgMonAn = (ImageView) convertView.findViewById(R.id.imgMonAn);
            holder.txtTenMonAn = (TextView) convertView.findViewById(R.id.txtTenMonAn);
            holder.txtGiaTien = (TextView) convertView.findViewById(R.id.txtGiaTien);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.imgMonAn.setImageURI(Uri.parse(dsMonAn.get(position).getHinhMonAn()));
        //Log.d("---link: ", dsMonAn.get(position).getHinhMonAn());
        Picasso.with(context).load(dsMonAn.get(position).getHinhMonAn()).into(holder.imgMonAn);
        holder.txtTenMonAn.setText(dsMonAn.get(position).getTenMonAn());
        holder.txtGiaTien.setText(dsMonAn.get(position).getGiaTien());

        return convertView;
    }
}
