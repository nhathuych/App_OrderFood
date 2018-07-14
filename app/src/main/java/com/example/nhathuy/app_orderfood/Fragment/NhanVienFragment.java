package com.example.nhathuy.app_orderfood.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nhathuy.app_orderfood.Activity.DangKyActivity;
import com.example.nhathuy.app_orderfood.Activity.TrangChuActivity;
import com.example.nhathuy.app_orderfood.Adapter.NhanVienAdapter;
import com.example.nhathuy.app_orderfood.DAO.NhanVienDAO;
import com.example.nhathuy.app_orderfood.DTO.NhanVien;
import com.example.nhathuy.app_orderfood.Static.QuyenNhanVien;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class NhanVienFragment extends Fragment {
    ListView lsvNhanVien;

    List<NhanVien> dsNhanVien;
    NhanVienAdapter adapter;
    NhanVienDAO nhanVienDAO;

    int maquyen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.nhanvien);
        View view = inflater.inflate(R.layout.layout_danhsach_nhanvien, container, false);
        setHasOptionsMenu(true);

        lsvNhanVien = (ListView) view.findViewById(R.id.lsvNhanVien);
        nhanVienDAO = new NhanVienDAO(getContext());

        capNhatDanhSachNhanVien();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen", 0);
        if (maquyen == QuyenNhanVien.ADMIN) {
            registerForContextMenu(lsvNhanVien);
        }

        return view;
    }

    private void capNhatDanhSachNhanVien() {
        dsNhanVien = nhanVienDAO.danhSachNhanVien();
        adapter = new NhanVienAdapter(getActivity(), R.layout.custon_layout_nhanvien, dsNhanVien);
        lsvNhanVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (maquyen == QuyenNhanVien.ADMIN) {
            getActivity().getMenuInflater().inflate(R.menu.grid_context_menu, menu);    // xài lại của gvdBanAn
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.itSua:
                Intent intent = new Intent(getActivity(), DangKyActivity.class);
                intent.putExtra("nhanvien", dsNhanVien.get(menuInfo.position));
                startActivity(intent);
                break;
            case R.id.itXoa:
                Snackbar snackbar = Snackbar.make(lsvNhanVien, "Xác nhận xóa nhân viên " + dsNhanVien.get(menuInfo.position).getTenDN() + " ?\n(tắt sau 5s)", 5000);
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ktra = nhanVienDAO.xoaNhanVien(dsNhanVien.get(menuInfo.position).getMaNV());
                        if (ktra) {
                            Snackbar.make(lsvNhanVien, R.string.xoathanhcong, Snackbar.LENGTH_SHORT).show();
                            capNhatDanhSachNhanVien();
                        } else {
                            Snackbar.make(lsvNhanVien, R.string.xoathatbai, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                snackbar.show();
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maquyen == QuyenNhanVien.ADMIN) {
            MenuItem menuItem = menu.add(1, R.id.itThemNhanVien, 1, R.string.themnhanvien);
            menuItem.setIcon(R.drawable.nhanvien);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itThemNhanVien) {
            startActivity(new Intent(getActivity(), DangKyActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
