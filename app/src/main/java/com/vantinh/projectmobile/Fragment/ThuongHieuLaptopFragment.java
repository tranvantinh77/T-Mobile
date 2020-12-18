package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    ImageView back_thuong_hieu_laptop;
    TextView ten_thuong_hieu_laptop;
    RecyclerView rcv_thuong_hieu_laptop;
    private int idsanphamlaptop;

    MainActivity mMainActivity;
    SanPhamAdapter sanPhamAdapter;

    View view;
    public ThuongHieuLaptopFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thuong_hieu_laptop, container, false);

        // ánh xạ
        back_thuong_hieu_laptop = view.findViewById(R.id.back_thuong_hieu_laptop);
        ten_thuong_hieu_laptop = view.findViewById(R.id.ten_thuong_hieu_laptop);
        rcv_thuong_hieu_laptop = view.findViewById(R.id.rcv_thuong_hieu_laptop);
        mMainActivity = (MainActivity) getActivity();


        // Back
        back_thuong_hieu_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
                    getFragmentManager().popBackStack();
                }
            }
        });
        // Nhận dữ liệu
        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            ThuongHieu thuongHieu = (ThuongHieu) bundleReceive.getSerializable("thuong_hieu");
            if (thuongHieu != null) {
                ten_thuong_hieu_laptop.setText(thuongHieu.getName());
                idsanphamlaptop = thuongHieu.getIdthuonghieu();
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
        final ArrayList<SanPham> mangsanpham = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanthlaptop, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    int id = 0;
                    String ten = "";
                    String hinhanh = "";
                    Integer gia = 0;
                    String thongsokithuat = "";
                    String mota = "";
                    int idsanphamlaptop = 0;
                    int idsanpham = 0;
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
                            idsanphamlaptop = jsonObject.getInt("idsanphamlaptop");
                            idsanpham = jsonObject.getInt("idsanpham");
                            mangsanpham.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanphamlaptop,idsanpham));
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
                param.put("idsanphamlaptop", String.valueOf(idsanphamlaptop));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return mangsanpham;
    }

}