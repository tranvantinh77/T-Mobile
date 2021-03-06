package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThuongHieuLaptopFragment extends Fragment {
    public static final String TAG = ThuongHieuLaptopFragment.class.getName();
    ImageView back_thuong_hieu_laptop, shopping_thlt;
    TextView ten_thuong_hieu_laptop,count_gio_hang;
    RecyclerView rcv_thuong_hieu_laptop;
    private int idloaisanpham;
    ArrayList<SanPham> mangthuonghieulaptop = new ArrayList<>();

    MainActivity mMainActivity;
    SanPhamAdapter sanPhamAdapter;

    View view;
    public ThuongHieuLaptopFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thuong_hieu_laptop, container, false);

        anhXa(view);

        if (MainActivity.manggiohang.size() > 0) {
            count_gio_hang.setText(String.valueOf(MainActivity.manggiohang.size()));
        }

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        // Back
        back_thuong_hieu_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_thlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    mMainActivity.goToLogin();
                }
            }
        });

        // Nhận dữ liệu
        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            ThuongHieu thuongHieu = (ThuongHieu) bundleReceive.getSerializable("thuong_hieu");
            if (thuongHieu != null) {
                ten_thuong_hieu_laptop.setText(thuongHieu.getName());
                idloaisanpham = thuongHieu.getIdthuonghieu();
            }
        }

        sanPhamAdapter = new SanPhamAdapter(getDataTHLaptop(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_thuong_hieu_laptop.setAdapter(sanPhamAdapter);
//        rcv_dien_thoai.setHasFixedSize(true);
        rcv_thuong_hieu_laptop.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view;
    }

    private ArrayList<SanPham> getDataTHLaptop() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datathuonghieu, new Response.Listener<String>() {
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
                            mangthuonghieulaptop.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham));
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
                param.put("idloaisanpham", String.valueOf(idloaisanpham));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return mangthuonghieulaptop;
    }

    public void anhXa(View view) {
        back_thuong_hieu_laptop = view.findViewById(R.id.back_thuong_hieu_laptop);
        shopping_thlt = view.findViewById(R.id.shopping_thlt);
        count_gio_hang = view.findViewById(R.id.count_gio_hangthlt);
        ten_thuong_hieu_laptop = view.findViewById(R.id.ten_thuong_hieu_laptop);
        rcv_thuong_hieu_laptop = view.findViewById(R.id.rcv_thuong_hieu_laptop);
        mMainActivity = (MainActivity) getActivity();
    }

}