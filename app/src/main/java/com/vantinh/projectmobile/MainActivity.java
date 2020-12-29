package com.vantinh.projectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vantinh.projectmobile.Fragment.ChiTietSPFragment;
import com.vantinh.projectmobile.Fragment.DienThoaiFragment;
import com.vantinh.projectmobile.Fragment.ForgetFragment;
import com.vantinh.projectmobile.Fragment.GioHangFragment;
import com.vantinh.projectmobile.Fragment.SanPhamSaleFragment;
import com.vantinh.projectmobile.Fragment.ThuongHieuDTFragment;
import com.vantinh.projectmobile.Fragment.LaptopFragment;
import com.vantinh.projectmobile.Fragment.LoginFragment;
import com.vantinh.projectmobile.Fragment.CategoryFragment;
import com.vantinh.projectmobile.Fragment.HomeFragment;
import com.vantinh.projectmobile.Fragment.PersonFragment;
import com.vantinh.projectmobile.Fragment.PhuKienFragment;
import com.vantinh.projectmobile.Fragment.RegisterFragment;
import com.vantinh.projectmobile.Fragment.ThuongHieuLaptopFragment;
import com.vantinh.projectmobile.Model.GioHang;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.ultil.CheckConnection;
import com.vantinh.projectmobile.ultil.CheckInternetActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    public static ArrayList<GioHang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }

        // Check Internet
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {

        } else {
            startActivity(new Intent(getApplicationContext(), CheckInternetActivity.class));
        }

        // Menu bottom
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentActivity,
                new HomeFragment()).commit();
        getSupportParentActivityIntent();
    }

        // chuyển fragment trong bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.itemHome:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.itemCategory:
                            selectedFragment = new CategoryFragment();
                            break;
                        case R.id.itemAccount:
                            selectedFragment = new PersonFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentActivity,
                            selectedFragment).commit();

                    return true;
                }
            };

    // Chuyển Fragment
    public void goToLogin() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,loginFragment,"loginFragment");
        fragmentTransaction.addToBackStack(LoginFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToRegister() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,registerFragment,"registerFragment");
        fragmentTransaction.addToBackStack(RegisterFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToForget() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ForgetFragment forgetFragment = new ForgetFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,forgetFragment);
        fragmentTransaction.addToBackStack(ForgetFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToDienThoai() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DienThoaiFragment dienThoaiFragment = new DienThoaiFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,dienThoaiFragment);
        fragmentTransaction.addToBackStack(DienThoaiFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToLaptop() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LaptopFragment laptopFragment = new LaptopFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,laptopFragment);
        fragmentTransaction.addToBackStack(LaptopFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToPhuKien() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PhuKienFragment phuKienFragment = new PhuKienFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,phuKienFragment);
        fragmentTransaction.addToBackStack(PhuKienFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToSale() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SanPhamSaleFragment sanPhamSaleFragment = new SanPhamSaleFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,sanPhamSaleFragment);
        fragmentTransaction.addToBackStack(SanPhamSaleFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToThuongHieuDienThoai(ThuongHieu thuongHieu) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ThuongHieuDTFragment thuongHieuDTFragment = new ThuongHieuDTFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("thuong_hieu", thuongHieu);
        thuongHieuDTFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,thuongHieuDTFragment);

        fragmentTransaction.addToBackStack(ThuongHieuDTFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToThuongHieuLaptop(ThuongHieu thuongHieu) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ThuongHieuLaptopFragment thuongHieuLaptopFragment = new ThuongHieuLaptopFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("thuong_hieu", thuongHieu);
        thuongHieuLaptopFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,thuongHieuLaptopFragment);

        fragmentTransaction.addToBackStack(ThuongHieuLaptopFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToCTSP(SanPham sanPham) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ChiTietSPFragment chiTietSPFragment = new ChiTietSPFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("dien_thoai", sanPham);
        chiTietSPFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,chiTietSPFragment);

        fragmentTransaction.addToBackStack(ChiTietSPFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToGioHang() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        GioHangFragment gioHangFragment = new GioHangFragment();

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,gioHangFragment);

        fragmentTransaction.addToBackStack(GioHangFragment.TAG);
        fragmentTransaction.commit();
    }

    public void finishFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag("registerFragment");
        if (registerFragment != null) {
            fragmentTransaction.remove(registerFragment);
            fragmentTransaction.commitNow();
        }

    }
}