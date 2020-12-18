package com.vantinh.projectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vantinh.projectmobile.Fragment.ChiTietSPFragment;
import com.vantinh.projectmobile.Fragment.DienThoaiFragment;
import com.vantinh.projectmobile.Fragment.ThuongHieuDTFragment;
import com.vantinh.projectmobile.Fragment.LaptopFragment;
import com.vantinh.projectmobile.Fragment.LoginFragment;
import com.vantinh.projectmobile.Fragment.CategoryFragment;
import com.vantinh.projectmobile.Fragment.HomeFragment;
import com.vantinh.projectmobile.Fragment.PersonFragment;
import com.vantinh.projectmobile.Fragment.PhuKienFragment;
import com.vantinh.projectmobile.Fragment.RegisterFragment;
import com.vantinh.projectmobile.Fragment.ThuongHieuLaptopFragment;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.ultil.CheckConnection;
import com.vantinh.projectmobile.ultil.CheckInternetActivity;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        bottomNavigationView = findViewById(R.id.bottom_navigation);

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
        LoginFragment blankFragment = new LoginFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,blankFragment);
        fragmentTransaction.addToBackStack(LoginFragment.TAG);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
    }

    public void goToRegister() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,registerFragment);
        fragmentTransaction.addToBackStack(RegisterFragment.TAG);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
    }

    public void goToDienThoai() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DienThoaiFragment dienThoaiFragment = new DienThoaiFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,dienThoaiFragment);
        fragmentTransaction.addToBackStack(DienThoaiFragment.TAG);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
    }

    public void goToLaptop() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LaptopFragment laptopFragment = new LaptopFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,laptopFragment);
        fragmentTransaction.addToBackStack(LaptopFragment.TAG);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
    }

    public void goToPhuKien() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PhuKienFragment phuKienFragment = new PhuKienFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity,phuKienFragment);
        fragmentTransaction.addToBackStack(PhuKienFragment.TAG);
        bottomNavigationView.setVisibility(View.INVISIBLE);
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
        bottomNavigationView.setVisibility(View.INVISIBLE);
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
        bottomNavigationView.setVisibility(View.INVISIBLE);
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
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
    }

}