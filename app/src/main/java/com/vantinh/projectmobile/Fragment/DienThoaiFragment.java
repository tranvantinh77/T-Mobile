package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.Adapter.ThuongHieuAdapter;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DienThoaiFragment extends Fragment {
    public static final String TAG = DienThoaiFragment.class.getName();
    ImageView back_dienthoai, shopping_dt;
    RecyclerView rcv_thuong_hieu, rcv_dien_thoai;
    SanPhamAdapter sanPhamAdapter;
    private MainActivity mMainActivity;
    View view;

    public DienThoaiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dien_thoai, container, false);

        anhXa(view);
        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);


        // thuong hieu
        ThuongHieuAdapter thuongHieuAdapter = new ThuongHieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false);
        rcv_thuong_hieu.setLayoutManager(linearLayoutManager);

        // Chuyển thương hiệu
        thuongHieuAdapter.setData(getListData(), new ThuongHieuAdapter.IClickItemListener() {
            @Override
            public void onClickItemTH(ThuongHieu thuongHieu) {
                mMainActivity.goToThuongHieuDienThoai(thuongHieu);
            }
        });

        rcv_thuong_hieu.setAdapter(thuongHieuAdapter);

        // Back
        back_dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToGioHang();
            }
        });

         sanPhamAdapter = new SanPhamAdapter(getDataDT(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_dien_thoai.setAdapter(sanPhamAdapter);
        rcv_dien_thoai.setHasFixedSize(true);
        rcv_dien_thoai.setLayoutManager(new GridLayoutManager(getContext(),2));


        return view;
    }

    private  ArrayList<SanPham> getDataDT() {
        final ArrayList<SanPham> mangsanpham = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                            mangsanpham.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanphamdienthoai,idsanpham));
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

    private List<ThuongHieu> getListData() {
        List<ThuongHieu> list = new ArrayList<>();

        list.add(new ThuongHieu(R.drawable.iphone, "Iphone",1));
        list.add(new ThuongHieu(R.drawable.samsung, "Samsung",2));
        list.add(new ThuongHieu(R.drawable.xiaomi, "Xiaomi",5));
        list.add(new ThuongHieu(R.drawable.vsmart, "Vsmart",6));
        list.add(new ThuongHieu(R.drawable.realme, "Realme",4));
        list.add(new ThuongHieu(R.drawable.oppo, "Oppo",3));

        return list;
    }

    public void anhXa(View view) {
        back_dienthoai = view.findViewById(R.id.back_dienthoai);
        shopping_dt = view.findViewById(R.id.shopping_dt);
        rcv_thuong_hieu = view.findViewById(R.id.rcv_thuong_hieu);
        rcv_dien_thoai = view.findViewById(R.id.rcv_dien_thoai);
        mMainActivity = (MainActivity) getActivity();
    }
}