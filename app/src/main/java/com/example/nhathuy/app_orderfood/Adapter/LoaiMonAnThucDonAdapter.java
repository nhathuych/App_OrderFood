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

import com.example.nhathuy.app_orderfood.DAO.LoaiMonAnDAO;
import com.example.nhathuy.app_orderfood.DTO.LoaiMonAn;
import com.example.nhathuy.app_orderfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LoaiMonAnThucDonAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAn> dsLoaiMon;
    LoaiMonAnDAO loaiMonAnDAO;

    public LoaiMonAnThucDonAdapter(Context context, int layout, List<LoaiMonAn> dsLoaiMon) {
        this.context = context;
        this.layout = layout;
        this.dsLoaiMon = dsLoaiMon;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
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

    class MyViewHolder {
        TextView txtTenLoaiThucDon;
        ImageView imgLoaiThucDon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);

            holder = new MyViewHolder();
            holder.txtTenLoaiThucDon = (TextView) convertView.findViewById(R.id.txtTenLoaiThucDon);
            holder.imgLoaiThucDon = (ImageView) convertView.findViewById(R.id.imgLoaiThucDon);

            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        LoaiMonAn loaiMonAn = dsLoaiMon.get(position);
        holder.txtTenLoaiThucDon.setText(loaiMonAn.getTenLoai());
        //holder.imgLoaiThucDon.setImageURI(Uri.parse(loaiMonAnDAO.layHinhLoaiMonAn(loaiMonAn.getMaLoai())));
        Picasso.with(context).load(loaiMonAnDAO.layHinhLoaiMonAn(loaiMonAn.getMaLoai())).into(holder.imgLoaiThucDon);
        //Log.d("---url: ", loaiMonAnDAO.layHinhLoaiMonAn(loaiMonAn.getMaLoai()));

        return convertView;
    }
}
