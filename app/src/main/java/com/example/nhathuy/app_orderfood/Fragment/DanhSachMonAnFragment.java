package com.example.nhathuy.app_orderfood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nhathuy.app_orderfood.Adapter.DanhSachMonAnAdapter;
import com.example.nhathuy.app_orderfood.DAO.MonAnDAO;
import com.example.nhathuy.app_orderfood.DTO.MonAn;
import com.example.nhathuy.app_orderfood.R;
import com.example.nhathuy.app_orderfood.Activity.SoLuongActivity;

import java.util.List;

public class DanhSachMonAnFragment extends Fragment {
    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAn> dsMonAn;
    DanhSachMonAnAdapter danhSachMonAnAdapter;
    int maban;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_xuatthucdon, container, false);

        gridView = (GridView) view.findViewById(R.id.grvThucDon);
        monAnDAO = new MonAnDAO(getActivity());

        // bắt sự kiện bấm nút back
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            maban = bundle.getInt("maban");
            dsMonAn = monAnDAO.layDsMonTheoLoai(bundle.getInt("maloai"));
            danhSachMonAnAdapter = new DanhSachMonAnAdapter(getActivity(), R.layout.custom_layout_dsmonan, dsMonAn);
            gridView.setAdapter(danhSachMonAnAdapter);
            danhSachMonAnAdapter.notifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (maban != 0) {
                        Intent intent = new Intent(getActivity(), SoLuongActivity.class);
                        intent.putExtra("maban", maban);
                        intent.putExtra("mamonan", dsMonAn.get(position).getMaMonAn());
                        startActivity(intent);
                    }
                }
            });
        }

        return view;
    }
}
