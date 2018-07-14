package com.example.nhathuy.app_orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.DAO.BanAnDAO;
import com.example.nhathuy.app_orderfood.DAO.GoiMonDAO;
import com.example.nhathuy.app_orderfood.DTO.BanAn;
import com.example.nhathuy.app_orderfood.DTO.GoiMon;
import com.example.nhathuy.app_orderfood.Fragment.ThucDonFragment;
import com.example.nhathuy.app_orderfood.R;
import com.example.nhathuy.app_orderfood.Activity.ThanhToanActivity;
import com.example.nhathuy.app_orderfood.Activity.TrangChuActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BanAnAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<BanAn> dsBanAn;

    ViewHolder holder;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;

    public BanAnAdapter(Context context, int layout, List<BanAn> dsBanAn) {
        this.context = context;
        this.layout = layout;
        this.dsBanAn = dsBanAn;
        banAnDAO = new BanAnDAO(context);
        goiMonDAO = new GoiMonDAO(context);
    }

    @Override
    public int getCount() {
        return dsBanAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsBanAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsBanAn.get(position).getMaBan();
    }

    private class ViewHolder {
        TextView txtTenBan;
        ImageView imgBanAn, imgGoiMon, imgThanhToan, imgAnButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.txtTenBan = (TextView) convertView.findViewById(R.id.txtTenBanAn);
            holder.imgBanAn = (ImageView) convertView.findViewById(R.id.imgBanAn);
            holder.imgGoiMon = (ImageView) convertView.findViewById(R.id.imgGoiMon);
            holder.imgThanhToan = (ImageView) convertView.findViewById(R.id.imgThanhToan);
            holder.imgAnButton = (ImageView) convertView.findViewById(R.id.imgAnButton);

            convertView.setTag(holder); // lưu cái holder lại
        } else {
            holder = (ViewHolder) convertView.getTag(); // lấy cái holder đã dc lưu ra xài
        }

        if(dsBanAn.get(position).isDuocChon()) {
            hienThiButton();
        } else {
            anButton(false);
        }

        String tinhTrang = banAnDAO.tinhTrangBan(dsBanAn.get(position).getMaBan());
        if (tinhTrang.equals("true")) {
            holder.imgBanAn.setImageResource(R.drawable.banantrue);
        } else {
            //holder.imgBanAn.setImageResource(R.drawable.banan);
        }

        holder.txtTenBan.setText(dsBanAn.get(position).getTenBan());
        holder.imgBanAn.setTag(position);

        holder.imgBanAn.setOnClickListener(this);
        holder.imgGoiMon.setOnClickListener(this);
        holder.imgThanhToan.setOnClickListener(this);
        holder.imgAnButton.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        holder = (ViewHolder) ((View) v.getParent()).getTag();
        int viTri = (int) holder.imgBanAn.getTag(); // hỏi google

        switch (v.getId()) {
            case R.id.imgBanAn:
                //int vitri = (int) holder.imgBanAn.getTag(); // hỏi google
                int possition = (int) v.getTag();   // hỏi google
                dsBanAn.get(possition).setDuocChon(true);
                hienThiButton();
                break;
            case R.id.imgGoiMon:
                Intent intent = ((TrangChuActivity) context).getIntent();
                int maNV = intent.getIntExtra("maNhanVien", 0);
                int maBan = dsBanAn.get(viTri).getMaBan();

                String tinhTrangBanAn = banAnDAO.tinhTrangBan(maBan);
                if (tinhTrangBanAn.equals("false")) {
                    // thêm bảng gọi món và cập nhật lại tình trạng bàn
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
                    String ngayGoi = dateFormat.format(calendar.getTime());

                    long ktra = goiMonDAO.themGoiMon(new GoiMon(maBan, maNV, "false", ngayGoi));
                    banAnDAO.capNhatTinhTrangBan(maBan, "true");
                    if (ktra == 0) {
                        Toast.makeText(context, R.string.themthatbai, Toast.LENGTH_SHORT).show();
                    }
                }

                Bundle bundle = new Bundle();
                bundle.putInt("maban", maBan);
                ThucDonFragment thucDonFragment = new ThucDonFragment();
                thucDonFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((TrangChuActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        replace(R.id.layout_content, thucDonFragment)
                        .addToBackStack("hienthibanan")
                        .commit();
                break;
            case R.id.imgThanhToan:
                Intent iThanhToan = new Intent(context, ThanhToanActivity.class);
                iThanhToan.putExtra("maban", dsBanAn.get(viTri).getMaBan());
                context.startActivity(iThanhToan);
                break;
            case R.id.imgAnButton:
                anButton(true);
                break;
            default:
                break;
        }
    }

    private void hienThiButton() {
        holder.imgGoiMon.setVisibility(View.VISIBLE);
        holder.imgThanhToan.setVisibility(View.VISIBLE);
        holder.imgAnButton.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_hienbutton_banan);
        holder.imgGoiMon.startAnimation(animation);
        holder.imgThanhToan.startAnimation(animation);
        holder.imgAnButton.startAnimation(animation);
    }

    // ẩn button
    private void anButton(boolean hieuUng) {
        if (hieuUng) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_anbutton_banan);
            holder.imgGoiMon.startAnimation(animation);
            holder.imgThanhToan.startAnimation(animation);
            holder.imgAnButton.startAnimation(animation);
        }

        holder.imgGoiMon.setVisibility(View.INVISIBLE);
        holder.imgThanhToan.setVisibility(View.INVISIBLE);
        holder.imgAnButton.setVisibility(View.INVISIBLE);
    }
}
