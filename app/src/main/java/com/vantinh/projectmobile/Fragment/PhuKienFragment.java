package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhuKienFragment extends Fragment {
    public static final String TAG = PhuKienFragment.class.getName();
    ImageView back_phukien, shopping_pk;
    RecyclerView  rcv_phu_hien;
    SanPhamAdapter sanPhamAdapter;
    MainActivity mMainActivity;

    View view;
    public PhuKienFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phu_kien, container, false);

        // ánh xạ
        back_phukien = view.findViewById(R.id.back_phukien);
        shopping_pk = view.findViewById(R.id.shopping_pk);
        rcv_phu_hien = view.findViewById(R.id.rcv_phu_kien);
        mMainActivity = (MainActivity) getActivity();


        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        back_phukien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mMainActivity.goToGioHang();
            }
        });

        sanPhamAdapter = new SanPhamAdapter(getDataPhuKien(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_phu_hien.setAdapter(sanPhamAdapter);
//        rcv_dien_thoai.setHasFixedSize(true);
        rcv_phu_hien.setLayoutManager(new GridLayoutManager(getContext(),2));


        return view;
    }

    private  ArrayList<SanPham> getDataPhuKien() {
        final ArrayList<SanPham> mangsanpham = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                            mangsanpham.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanpham));
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
        return mangsanpham;
    }
}