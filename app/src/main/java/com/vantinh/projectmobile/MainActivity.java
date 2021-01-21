package com.vantinh.projectmobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.Fragment.ChangePasswordFragment;
import com.vantinh.projectmobile.Fragment.ChiTietSPFragment;
import com.vantinh.projectmobile.Fragment.DienThoaiFragment;
import com.vantinh.projectmobile.Fragment.ForgetFragment;
import com.vantinh.projectmobile.Fragment.GioHangFragment;
import com.vantinh.projectmobile.Fragment.SearchFragment;
import com.vantinh.projectmobile.Fragment.ThanhToanFragment;
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
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginFragment.ISendDataListener {
    public static BottomNavigationView bottomNavigationView;
    //mang san pham
    public static ArrayList<GioHang> manggiohang = new ArrayList<>();
    public static ArrayList<SanPham> mangdienthoai = new ArrayList<>();
    public static ArrayList<SanPham> manglaptop = new ArrayList<>();
    public static ArrayList<SanPham> mangphukien = new ArrayList<>();

    public static ArrayList<SanPham> dienthoainoibat = new ArrayList<>();
    public static ArrayList<SanPham> laptopnoibat = new ArrayList<>();
    public static ArrayList<SanPham> phukiennoibat = new ArrayList<>();
    public SanPhamAdapter sanPhamAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        createArray();

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

    private void createArray() {
        if (manggiohang != null) {


        } else {
            manggiohang = new ArrayList<>();
        }

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

    public ArrayList<SanPham> dataDienThoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                String hinhanh = "";
                String hinhanh2 = "";
                String hinhanh3 = "";
                String hinhanh4 = "";
                Integer gia = 0;
                String thongsokithuat = "";
                String mota = "";
                int idloaisanpham = 0;
                int idsanpham = 0;
                int status = 0;
                if (response != null) {
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            hinhanh2 = jsonObject.getString("hinhanh2");
                            hinhanh3 = jsonObject.getString("hinhanh3");
                            hinhanh4 = jsonObject.getString("hinhanh4");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idloaisanpham = jsonObject.getInt("idloaisanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            status = jsonObject.getInt("status");
                            MainActivity.mangdienthoai.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            sanPhamAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(1));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.mangdienthoai;
    }

    public ArrayList<SanPham> dataLaptop() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                String hinhanh = "";
                String hinhanh2 = "";
                String hinhanh3 = "";
                String hinhanh4 = "";
                Integer gia = 0;
                String thongsokithuat = "";
                String mota = "";
                int idloaisanpham = 0;
                int idsanpham = 0;
                int status = 0;
                if (response != null) {
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            hinhanh2 = jsonObject.getString("hinhanh2");
                            hinhanh3 = jsonObject.getString("hinhanh3");
                            hinhanh4 = jsonObject.getString("hinhanh4");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idloaisanpham = jsonObject.getInt("idloaisanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            status = jsonObject.getInt("status");
                            MainActivity.manglaptop.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            sanPhamAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(2));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.manglaptop;
    }

    public ArrayList<SanPham> dataPhuKien() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                String hinhanh = "";
                String hinhanh2 = "";
                String hinhanh3 = "";
                String hinhanh4 = "";
                Integer gia = 0;
                String thongsokithuat = "";
                String mota = "";
                int idloaisanpham = 0;
                int idsanpham = 0;
                int status = 0;
                if (response != null) {
                    try {
                        JSONArray jsonArray =new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            hinhanh2 = jsonObject.getString("hinhanh2");
                            hinhanh3 = jsonObject.getString("hinhanh3");
                            hinhanh4 = jsonObject.getString("hinhanh4");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idloaisanpham = jsonObject.getInt("idloaisanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            status = jsonObject.getInt("status");
                            MainActivity.mangphukien.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            sanPhamAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(3));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.mangphukien;
    }

    public void xoa(final int position) {
        if (manggiohang.size() <= 0) {
            GioHangFragment.txt_thong_bao.setVisibility(View.VISIBLE);
        } else {
            manggiohang.remove(position);
            GioHangFragment.gioHangAdapter.notifyDataSetInvalidated();
            GioHangFragment.eventUtil();
            if (MainActivity.manggiohang.size() <= 0) {
                GioHangFragment.txt_thong_bao.setVisibility(View.VISIBLE);
                GioHangFragment.btn_mua_ngay.setEnabled(false);
                GioHangFragment.btn_mua_ngay.setBackgroundColor(Color.DKGRAY);
                } else {
                GioHangFragment.txt_thong_bao.setVisibility(View.INVISIBLE);
                GioHangFragment.gioHangAdapter.notifyDataSetInvalidated();
                GioHangFragment.eventUtil();
            }
        }
        Toast.makeText(getApplicationContext(),"Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
    }

    // Chuyển Fragment
    public void goToLogin() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, loginFragment);
        fragmentTransaction.addToBackStack(LoginFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToDXuat() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PersonFragment personFragment = new PersonFragment();
        fragmentTransaction.replace(R.id.fragmentActivity, personFragment);
        fragmentTransaction.addToBackStack(personFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToRegister() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, registerFragment);
        fragmentTransaction.addToBackStack(RegisterFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToForget() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ForgetFragment forgetFragment = new ForgetFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, forgetFragment);
        fragmentTransaction.addToBackStack(ForgetFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToDienThoai() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DienThoaiFragment dienThoaiFragment = new DienThoaiFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, dienThoaiFragment);
        fragmentTransaction.addToBackStack(DienThoaiFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToLaptop() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LaptopFragment laptopFragment = new LaptopFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, laptopFragment);
        fragmentTransaction.addToBackStack(LaptopFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToChangePassword() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, changePasswordFragment);
        fragmentTransaction.addToBackStack(ChangePasswordFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToPhuKien() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PhuKienFragment phuKienFragment = new PhuKienFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, phuKienFragment);
        fragmentTransaction.addToBackStack(PhuKienFragment.TAG);
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
                .replace(R.id.fragmentActivity, thuongHieuDTFragment);

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
                .replace(R.id.fragmentActivity, thuongHieuLaptopFragment);

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
                .replace(R.id.fragmentActivity, chiTietSPFragment);

        fragmentTransaction.addToBackStack(ChiTietSPFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToSearch(SanPham sanPham) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        SearchFragment searchFragment = new SearchFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("dien_thoai", sanPham);
        searchFragment.setArguments(bundle);

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, searchFragment);

        fragmentTransaction.addToBackStack(searchFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToGioHang() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        GioHangFragment gioHangFragment = new GioHangFragment();

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, gioHangFragment);

        fragmentTransaction.addToBackStack(GioHangFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToThanhToan() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ThanhToanFragment thanhToanFragment = new ThanhToanFragment();

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, thanhToanFragment);

        fragmentTransaction.addToBackStack(ThanhToanFragment.TAG);
        fragmentTransaction.commit();
    }

    public void goToHome() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        HomeFragment homeFragment = new HomeFragment();

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, homeFragment);

        fragmentTransaction.addToBackStack(HomeFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void senData(String fullname) {
        PersonFragment personFragment = (PersonFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentActivity);
        if (personFragment != null) {
            personFragment.receviceDataFromLogin(fullname);
        }
    }
}
