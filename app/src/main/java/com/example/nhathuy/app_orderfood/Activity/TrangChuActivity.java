package com.example.nhathuy.app_orderfood.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nhathuy.app_orderfood.Fragment.BanAnFragment;
import com.example.nhathuy.app_orderfood.Fragment.NhanVienFragment;
import com.example.nhathuy.app_orderfood.Fragment.ThucDonFragment;
import com.example.nhathuy.app_orderfood.R;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;  // cái này chứa NavigationView và Toolbar
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtTenNV_NavigationView, txtEmail_NavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        initView();
        navigationView.setNavigationItemSelectedListener(this);
        // Mặc định mở cái Fragment bàn ăn đầu tiên
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, new BanAnFragment()).commit();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationViewTrangChu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setTextChoTextView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // display cái button mũi tên trở về. Bỏ cái này đi thì cái drawerToggle cũng mất ?
        // display cái button 3 gạch(drawer toggle)
        // truyền toolbar vào ActionBarDrawerToggle nó tự hiển thị cái NavigationView
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        // NavigationView tự động tô màu trắng vô các icon -> truyền null vào cho nó khỏi tô màu tầm bậy
        navigationView.setItemIconTintList(null);

//        // <!-- app:headerLayout="@layout/layout_header_navigation" -->
//        // <!-- app:menu="@menu/menu_navigation" -->
//        navigationView.inflateHeaderView(R.layout.layout_header_navigation);
//        navigationView.inflateMenu(R.menu.menu_navigation);
    }

    private void setTextChoTextView() {
        // <!-- app:headerLayout="@layout/layout_header_navigation" -->
        // <!-- app:menu="@menu/menu_navigation" -->
        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation);
        navigationView.inflateMenu(R.menu.menu_navigation);

        txtTenNV_NavigationView = (TextView) view.findViewById(R.id.txtTenNV_NavigationView);
        txtEmail_NavigationView = (TextView) view.findViewById(R.id.txtEmail_NavigationView);
        String taikhoan = getIntent().getStringExtra("taikhoan");
        txtTenNV_NavigationView.setText(taikhoan);
        txtEmail_NavigationView.setText(taikhoan.concat("@orderfood.com"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itTrangChu:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, new BanAnFragment()).commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itThucDon:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, new ThucDonFragment()).commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itNhanVien:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, new NhanVienFragment()).commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itThongKe:
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itCaiDat:
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }

        return false;
    }
}
