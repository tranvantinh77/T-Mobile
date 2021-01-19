package com.vantinh.projectmobile.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.Adapter.SearchAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
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
    public static final String TAG = HomeFragment.class.getName();
    Toolbar toolbar;
    Button btn_search;
    ImageSlider imageSlider;
    ImageView shopping_cart;
    TextView xem_san_pham_pk, xem_dien_thoai, xem_laptop;
    RecyclerView rcv_san_pham_sale, rcv_dien_thoai_noibat, rcv_laptop_noibat;
    Dialog dialog;

    private MainActivity mMainActivity;
    InputMethodManager imm ;
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
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        xem_san_pham_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToPhuKien();
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
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    mMainActivity.goToLogin();
                }
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.search);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                ImageView back_search = dialog.findViewById(R.id.back_search);
                final EditText edt_search = dialog.findViewById(R.id.edt_search);
                final RecyclerView rcv_search = dialog.findViewById(R.id.rcv_search);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                rcv_search.setLayoutManager(linearLayoutManager);
                RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                rcv_search.addItemDecoration(decoration);


                final SearchAdapter searchAdapter = new SearchAdapter(mMainActivity.getall(), new SearchAdapter.IClickItemListener() {
                    @Override
                    public void onClickItem(SanPham sanPham) {
                        imm.hideSoftInputFromWindow(edt_search.getWindowToken(), 0);
                        mMainActivity.goToCTSP(sanPham);
                        dialog.dismiss();
                    }
                });

                edt_search.requestFocus();
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                edt_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                            searchAdapter.getFilter().filter(s);
                            rcv_search.setAdapter(searchAdapter);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                back_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imm.hideSoftInputFromWindow(edt_search.getWindowToken(), 0);
                        dialog.dismiss();
                    }
                });

            }
        });

        mMainActivity.sanPhamAdapter = new SanPhamAdapter(getDataPKNT(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_san_pham_sale.setAdapter(mMainActivity.sanPhamAdapter);
        rcv_san_pham_sale.setHasFixedSize(true);
        rcv_san_pham_sale.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        mMainActivity.sanPhamAdapter = new SanPhamAdapter(getDataDT(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_dien_thoai_noibat.setAdapter(mMainActivity.sanPhamAdapter);
        rcv_dien_thoai_noibat.setHasFixedSize(true);
        rcv_dien_thoai_noibat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        mMainActivity.sanPhamAdapter = new SanPhamAdapter(getDataLaptop(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_laptop_noibat.setAdapter(mMainActivity.sanPhamAdapter);
        rcv_laptop_noibat.setHasFixedSize(true);
        rcv_laptop_noibat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        return view;
    }


    private ArrayList<SanPham> getDataPKNT() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sanphamnoibat, new Response.Listener<String>() {
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
                            MainActivity.phukiennoibat.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            mMainActivity.sanPhamAdapter.notifyDataSetChanged();
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
                param.put("status", String.valueOf(1));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.phukiennoibat;
    }

    private ArrayList<SanPham> getDataDT() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sanphamnoibat, new Response.Listener<String>() {
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
                            MainActivity.dienthoainoibat.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            mMainActivity.sanPhamAdapter.notifyDataSetChanged();
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
                param.put("status", String.valueOf(1));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.dienthoainoibat;
    }

    private ArrayList<SanPham> getDataLaptop() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sanphamnoibat, new Response.Listener<String>() {
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
                            MainActivity.laptopnoibat.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
                            mMainActivity.sanPhamAdapter.notifyDataSetChanged();
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
                param.put("status", String.valueOf(1));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return MainActivity.laptopnoibat;
    }

    public void anhXa(View view) {
        imageSlider = view.findViewById(R.id.slider);
        xem_san_pham_pk = view.findViewById(R.id.xem_san_pham_pk);
        xem_dien_thoai = view.findViewById(R.id.xem_dien_thoai);
        xem_laptop = view.findViewById(R.id.xem_laptop);
        toolbar = view.findViewById(R.id.toolbarHome);
        shopping_cart = view.findViewById(R.id.shopping_home);
        rcv_san_pham_sale = view.findViewById(R.id.rcv_san_pham_sale);
        rcv_dien_thoai_noibat = view.findViewById(R.id.rcv_dien_thoai_noibat);
        rcv_laptop_noibat = view.findViewById(R.id.rcv_laptop_noibat);
        btn_search = view.findViewById(R.id.btnsearch);
        mMainActivity = (MainActivity) getActivity();
    }
}
