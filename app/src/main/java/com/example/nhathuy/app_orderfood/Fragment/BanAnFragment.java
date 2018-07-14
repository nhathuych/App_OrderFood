package com.example.nhathuy.app_orderfood.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nhathuy.app_orderfood.Activity.SuaBanAnActivity;
import com.example.nhathuy.app_orderfood.Activity.ThemBanAnActivity;
import com.example.nhathuy.app_orderfood.Activity.TrangChuActivity;
import com.example.nhathuy.app_orderfood.Adapter.BanAnAdapter;
import com.example.nhathuy.app_orderfood.DAO.BanAnDAO;
import com.example.nhathuy.app_orderfood.DTO.BanAn;
import com.example.nhathuy.app_orderfood.Static.QuyenNhanVien;
import com.example.nhathuy.app_orderfood.R;

import java.util.List;

public class BanAnFragment extends Fragment {
    GridView grvBanAn;
    BanAnAdapter adapter;
    List<BanAn> dsBanAn;
    BanAnDAO banAnDAO;

    public static int REQUEST_CODE_THEM = 96;
    public static int REQUEST_CODE_SUA = 116;

    int maquyen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_banan, container, false);
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.banan);
        // thêm cái OptionsMenu ở BanAnFragment
        setHasOptionsMenu(true);

        grvBanAn = (GridView) view.findViewById(R.id.grvBanAn);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen", 0);

        banAnDAO = new BanAnDAO(getActivity());
        hienThiDanhSachBanAn();

        // quyền admin
        if (maquyen == QuyenNhanVien.ADMIN) {
            registerForContextMenu(grvBanAn);
        }

        return view;
    }

    private void hienThiDanhSachBanAn() {
        dsBanAn = banAnDAO.layDanhSachBanAn();
        adapter = new BanAnAdapter(getContext(), R.layout.custom_layout_banan, dsBanAn);
        grvBanAn.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.grid_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.itSua:
                Intent intent = new Intent(getActivity(), SuaBanAnActivity.class);
                intent.putExtra("maban", dsBanAn.get(menuInfo.position).getMaBan());
                startActivityForResult(intent, REQUEST_CODE_SUA);
                break;
            case R.id.itXoa:
                boolean res = banAnDAO.xoaBanAn(dsBanAn.get(menuInfo.position).getMaBan());
                if (res) {
                    hienThiDanhSachBanAn();
                    Toast.makeText(getActivity(), R.string.xoathanhcong, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.xoathatbai, Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // quyền admin
        if (maquyen == QuyenNhanVien.ADMIN) {
            MenuItem itThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.thembanan);
            itThemBanAn.setIcon(R.drawable.thembanan);
            itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.itThemBanAn) {
            //startActivity(new Intent(BanAnFragment.this, ThemBanAnActivity.class));   // sai
            // activity ở đây là TrangChuActivity, còn BanAnFragment chỉ là 1 cái fragment của TrangChuActivity thôi
            startActivityForResult(new Intent(getActivity(), ThemBanAnActivity.class), REQUEST_CODE_THEM);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_THEM && resultCode == Activity.RESULT_OK) {
            boolean ktra = data.getBooleanExtra("ketQuaThem", false);
            if (ktra == true) {
                hienThiDanhSachBanAn();
                Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQUEST_CODE_SUA && resultCode == Activity.RESULT_OK) {
            boolean ktra = data.getBooleanExtra("kiemtra", false);
            if (ktra) {
                hienThiDanhSachBanAn();
                Toast.makeText(getActivity(), R.string.suathanhcong, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.loi, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hienThiDanhSachBanAn();
    }
}
