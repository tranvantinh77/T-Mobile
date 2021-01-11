package com.vantinh.projectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.Fragment.ChangePasswordFragment;
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
import com.vantinh.projectmobile.Model.SanPhamSale;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.ultil.CheckConnection;
import com.vantinh.projectmobile.ultil.CheckInternetActivity;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoginFragment.ISendDataListener {
    public static BottomNavigationView bottomNavigationView;
    //mang san pham
    public static ArrayList<GioHang> manggiohang;
    public static ArrayList<SanPham> mangdienthoai = new ArrayList<>();
    public static ArrayList<SanPham> manglaptop = new ArrayList<>();
    public static ArrayList<SanPham> mangphukien = new ArrayList<>();
    public static ArrayList<SanPham> mangsearch = new ArrayList<>();

    public static ArrayList<SanPhamSale> mangsanphamsale = new ArrayList<>();
    public static ArrayList<SanPhamSale> sanPhamSales = new ArrayList<>();
    public static ArrayList<SanPham> dienthoainoibat = new ArrayList<>();
    public static ArrayList<SanPham> laptopnoibat = new ArrayList<>();

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

        if (sanPhamSales != null) {

        } else {
            sanPhamSales = new ArrayList<>();
        }

        if (dienthoainoibat != null) {

        } else {
            dienthoainoibat = new ArrayList<>();
        }

        if (laptopnoibat != null) {

        } else {
            laptopnoibat = new ArrayList<>();
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


    public ArrayList<SanPham> getDataDT() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandienthoai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String ten = "";
                    String hinhanh = "";
                    Integer gia = 0;
                    String thongsokithuat = "";
                    String mota = "";
                    int idsanphamdienthoai = 0;
                    int idsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanphamdienthoai = jsonObject.getInt("idsanphamdienthoai");
                            idsanpham = jsonObject.getInt("idsanpham");
                            MainActivity.mangdienthoai.add(new SanPham(id, ten, hinhanh, gia, thongsokithuat, mota, idsanphamdienthoai, idsanpham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return MainActivity.mangdienthoai;
    }

    public ArrayList<SanPham> getDataLaptop() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanlaptop, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String ten = "";
                    String hinhanh = "";
                    Integer gia = 0;
                    String thongsokithuat = "";
                    String mota = "";
                    int idsanphamlaptop = 0;
                    int idsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanphamlaptop = jsonObject.getInt("idsanphamlaptop");
                            idsanpham = jsonObject.getInt("idsanpham");
                            MainActivity.manglaptop.add(new SanPham(id, ten, hinhanh, gia, thongsokithuat, mota, idsanphamlaptop, idsanpham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return MainActivity.manglaptop;
    }

    public ArrayList<SanPham> getDataPhuKien() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanphukien, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String ten = "";
                    String hinhanh = "";
                    Integer gia = 0;
                    String thongsokithuat = "";
                    String mota = "";
                    int idsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanpham = jsonObject.getInt("idsanpham");
                            MainActivity.mangphukien.add(new SanPham(id, ten, hinhanh, gia, thongsokithuat, mota, idsanpham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return MainActivity.mangphukien;
    }


    public ArrayList<SanPham> getsanphamAll() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getalldata, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id = 0;
                    String ten = "";
                    String hinhanh = "";
                    Integer gia = 0;
                    String thongsokithuat = "";
                    String mota = "";
                    int idsanphamdienthoai = 0;
                    int idsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            hinhanh = jsonObject.getString("hinhanh");
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanphamdienthoai = jsonObject.getInt("idsanphamdienthoai");
                            idsanpham = jsonObject.getInt("idsanpham");
                            MainActivity.mangsearch.add(new SanPham(id, ten, hinhanh, gia, thongsokithuat, mota, idsanphamdienthoai, idsanpham));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return MainActivity.mangsearch;
    }

    public ArrayList<SanPham> getall() {
//        getDataDT();
//        getDataLaptop();
//        getDataPhuKien();

        for (SanPham sanPham : mangdienthoai) {
            mangsearch.add(sanPham);
        }
        for (SanPham sanPham : manglaptop) {
            mangsearch.add(sanPham);
        }
        for (SanPham sanPham : mangphukien) {
            mangsearch.add(sanPham);
        }
        return  mangsearch;
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

    public void goToSale() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SanPhamSaleFragment sanPhamSaleFragment = new SanPhamSaleFragment();
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, sanPhamSaleFragment);
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

    public void goToGioHang() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        GioHangFragment gioHangFragment = new GioHangFragment();

        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.fragmentActivity, gioHangFragment);

        fragmentTransaction.addToBackStack(GioHangFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void senData(String fullname) {
        PersonFragment personFragment = (PersonFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentActivity);
        if (personFragment != null) {
            personFragment.receviceDataFromLogin(fullname);
        }
    }
}//chỗ nào xử lý case login tui viết sử lý trong cái LoginFragment
