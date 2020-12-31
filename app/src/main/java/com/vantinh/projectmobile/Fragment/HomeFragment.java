package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.vantinh.projectmobile.Adapter.SaleAdapter;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.SanPhamSale;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    Toolbar toolbar;
    ImageSlider imageSlider;
    ImageView shopping_cart;
    TextView xem_san_pham_sale, xem_dien_thoai, xem_laptop;
    RecyclerView rcv_san_pham_sale, rcv_dien_thoai_noibat, rcv_laptop_noibat;

    private MainActivity mMainActivity;
    SaleAdapter saleAdapter;
    SanPhamAdapter sanPhamAdapter;
    private int statussale = 1;
    private int statusdt = 1;
    private int statuslaptop = 1;
    View view;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        // slide
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/mi-10-ultra-1.jpg"));
        slideModels.add(new SlideModel("https://cdn.mobilecity.vn/mobilecity-vn/images/2020/06/xiaomi-redmi-10x.jpg"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/11/banner/800-300-800x300-6.png"));
        slideModels.add(new SlideModel("https://cdn.cellphones.com.vn/media/ltsoft/promotion/MI_NOTE_10.png"));
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/k30-ultra-1.jpg"));
        imageSlider.setImageList(slideModels,true);

        xem_san_pham_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToSale();
            }
        });

        xem_dien_thoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToDienThoai();
            }
        });

        xem_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLaptop();
            }
        });

        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToGioHang();
            }
        });

        saleAdapter = new SaleAdapter(getDataSale(), new SaleAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPhamSale sanPhamSale) {

            }
        });
        rcv_san_pham_sale.setAdapter(saleAdapter);
        rcv_san_pham_sale.setHasFixedSize(true);
        rcv_san_pham_sale.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        sanPhamAdapter = new SanPhamAdapter(getDataDT(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_dien_thoai_noibat.setAdapter(sanPhamAdapter);
        rcv_dien_thoai_noibat.setHasFixedSize(true);
        rcv_dien_thoai_noibat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        sanPhamAdapter = new SanPhamAdapter(getDataLaptop(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_laptop_noibat.setAdapter(sanPhamAdapter);
        rcv_laptop_noibat.setHasFixedSize(true);
        rcv_laptop_noibat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        return view;
    }


    private ArrayList<SanPhamSale> getDataSale() {
        final ArrayList<SanPhamSale> sanPhamSales = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansale, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                Integer gia = 0;
                Integer giasale = 0;
                String hinhanh = "";
                String thongsokithuat = "";
                String mota = "";
                int status = 0;
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("ten");
                            gia = jsonObject.getInt("gia");
                            giasale = jsonObject.getInt("giasale");
                            hinhanh = jsonObject.getString("hinhanh");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            status = jsonObject.getInt("status");
                            sanPhamSales.add(new SanPhamSale(id,ten,gia,giasale,hinhanh,thongsokithuat,mota,status));
                            saleAdapter.notifyDataSetChanged();
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
                param.put("status", String.valueOf(statussale));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return sanPhamSales;
    }

    private ArrayList<SanPham> getDataDT() {
        final ArrayList<SanPham> dienthoai = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandienthoainoibat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                String hinhanh = "";
                Integer gia = 0;
                String thongsokithuat = "";
                String mota = "";
                int idsanphamdienthoai = 0;
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
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanphamdienthoai = jsonObject.getInt("idsanphamdienthoai");
                            idsanpham = jsonObject.getInt("idsanpham");
                            status = jsonObject.getInt("status");
                            dienthoai.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanphamdienthoai,idsanpham,status));
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
                param.put("status", String.valueOf(statusdt));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return dienthoai;
    }

    private ArrayList<SanPham> getDataLaptop() {
        final ArrayList<SanPham> laptop = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanlaptopnoibat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String ten = "";
                String hinhanh = "";
                Integer gia = 0;
                String thongsokithuat = "";
                String mota = "";
                int idsanphamdienthoai = 0;
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
                            gia = jsonObject.getInt("gia");
                            thongsokithuat = jsonObject.getString("thongsokithuat");
                            mota = jsonObject.getString("mota");
                            idsanphamdienthoai = jsonObject.getInt("idsanphamlaptop");
                            idsanpham = jsonObject.getInt("idsanpham");
                            status = jsonObject.getInt("status");
                            laptop.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanphamdienthoai,idsanpham,status));
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
                param.put("status", String.valueOf(statuslaptop));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return laptop;
    }

    public void anhXa(View view) {
        imageSlider = view.findViewById(R.id.slider);
        xem_san_pham_sale = view.findViewById(R.id.xem_san_pham_sale);
        xem_dien_thoai = view.findViewById(R.id.xem_dien_thoai);
        xem_laptop = view.findViewById(R.id.xem_laptop);
        toolbar = view.findViewById(R.id.toolbarHome);
        shopping_cart = view.findViewById(R.id.shopping_home);
        rcv_san_pham_sale = view.findViewById(R.id.rcv_san_pham_sale);
        rcv_dien_thoai_noibat = view.findViewById(R.id.rcv_dien_thoai_noibat);
        rcv_laptop_noibat = view.findViewById(R.id.rcv_laptop_noibat);
        mMainActivity = (MainActivity) getActivity();
    }
}
