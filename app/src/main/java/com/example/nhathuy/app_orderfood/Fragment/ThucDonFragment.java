package com.example.nhathuy.app_orderfood.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nhathuy.app_orderfood.Activity.ThemThucDonActivity;
import com.example.nhathuy.app_orderfood.Activity.TrangChuActivity;
import com.example.nhathuy.app_orderfood.Adapter.LoaiMonAnThucDonAdapter;
import com.example.nhathuy.app_orderfood.DAO.LoaiMonAnDAO;
import com.example.nhathuy.app_orderfood.DTO.LoaiMonAn;
import com.example.nhathuy.app_orderfood.Static.QuyenNhanVien;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class ThucDonFragment extends Fragment {
    GridView grvThucDon;
    List<LoaiMonAn> dsLoaiMon;
    LoaiMonAnThucDonAdapter adapter;

    //LoaiMonAnDAO loaiMonAnDAO;
    int maBan;
    int maquyen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xuatthucdon, container, false);
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.thucdon);

        setHasOptionsMenu(true);
        grvThucDon = (GridView) view.findViewById(R.id.grvThucDon);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen", 0);

        capNhatGiaoDien();

        Bundle bundleDuLieuThucDon = getArguments();
        if (bundleDuLieuThucDon != null) {
            maBan = bundleDuLieuThucDon.getInt("maban");
        }

        grvThucDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DanhSachMonAnFragment danhSachMonAnFragment = new DanhSachMonAnFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai", dsLoaiMon.get(position).getMaLoai());
                bundle.putInt("maban", maBan);
                danhSachMonAnFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_content, danhSachMonAnFragment)
                        .addToBackStack("hienthiloai")
                        .commit();
            }
        });

        return view;
    }

    private void capNhatGiaoDien() {
        dsLoaiMon = new LoaiMonAnDAO(getContext()).layDanhSachLoaiMonAn();
        adapter = new LoaiMonAnThucDonAdapter(getContext(), R.layout.custom_layout_loai_mon_an, dsLoaiMon);
        grvThucDon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maquyen == QuyenNhanVien.ADMIN) {
            MenuItem itThemThucDon = menu.add(1, R.id.itThemThucDon, 1, R.string.themthucdon);
            itThemThucDon.setIcon(R.drawable.logodangnhap);
            itThemThucDon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.itThemThucDon) {
            startActivity(new Intent(getActivity(), ThemThucDonActivity.class));
            //getActivity().overridePendingTransition(R.anim.anim_activity_ra, R.anim.anim_activity_vo);
        }

        return super.onOptionsItemSelected(item);
    }
}
