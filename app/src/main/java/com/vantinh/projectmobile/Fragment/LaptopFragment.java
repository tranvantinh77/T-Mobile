package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.Adapter.ThuongHieuAdapter;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LaptopFragment extends Fragment {
    public static final String TAG = LaptopFragment.class.getName();
    ImageView back_laptop, shopping_lt;
    RecyclerView rcv_thuong_hieu_lt, rcv_laptop;
    SanPhamAdapter sanPhamAdapter;
    MainActivity mMainActivity;

    View view;
    public LaptopFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_laptop, container, false);

        anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
        ThuongHieuAdapter thuongHieuAdapter = new ThuongHieuAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false);
        rcv_thuong_hieu_lt.setLayoutManager(linearLayoutManager);
        thuongHieuAdapter.setData(getListDataLaptop(), new ThuongHieuAdapter.IClickItemListener() {
            @Override
            public void onClickItemTH(ThuongHieu thuongHieu) {
                mMainActivity.goToThuongHieuLaptop(thuongHieu);

            }
        });
        rcv_thuong_hieu_lt.setAdapter(thuongHieuAdapter);

        back_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    Toast.makeText(getContext(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sanPhamAdapter = new SanPhamAdapter(getDataLaptop(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_laptop.setAdapter(sanPhamAdapter);
        rcv_laptop.setHasFixedSize(true);
        rcv_laptop.setLayoutManager(new GridLayoutManager(getContext(),2));


        return view;
    }

    private  ArrayList<SanPham> getDataLaptop() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                            MainActivity.manglaptop.add(new SanPham(id,ten,hinhanh,gia,thongsokithuat,mota,idsanphamlaptop,idsanpham));
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

    private List<ThuongHieu> getListDataLaptop() {
        List<ThuongHieu> list = new ArrayList<>();

        list.add(new ThuongHieu(R.drawable.dell, "DELL",1));
        list.add(new ThuongHieu(R.drawable.asus, "ASUS",3));
        list.add(new ThuongHieu(R.drawable.acer, "ACER",2));
        list.add(new ThuongHieu(R.drawable.hp, "HP",4));

        return list;
    }

    public void anhXa(View view) {
        back_laptop = view.findViewById(R.id.back_laptop);
        shopping_lt = view.findViewById(R.id.shopping_lt);
        rcv_thuong_hieu_lt = view.findViewById(R.id.rcv_thuong_hieu_lt);
        rcv_laptop = view.findViewById(R.id.rcv_laptop);
        mMainActivity = (MainActivity) getActivity();
    }
}