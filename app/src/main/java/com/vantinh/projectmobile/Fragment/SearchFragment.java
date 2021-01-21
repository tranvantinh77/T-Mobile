package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.Adapter.SearchAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment {
    public static final String TAG = SearchFragment.class.getName();
    RecyclerView rcv_search;
    ImageView back_search, shopping_search;
    TextView count_gio_hang_search;
    int Idsanpham = 0;
    int Idloaisanpham = 0;
    ArrayList<SanPham> listDataSearch;
    SanPhamAdapter sanPhamAdapter;
    MainActivity mMainActivity;

    public SearchFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        anhXa(view);
        mMainActivity = (MainActivity) getActivity();

        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            SanPham sanPham = (SanPham) bundleReceive.getSerializable("dien_thoai");
            if (sanPham != null) {
                Idsanpham = sanPham.getIDsanpham();
                Idloaisanpham = sanPham.getIDloaisanpham();
                Log.d("a", String.valueOf(Idloaisanpham));
                Log.d("b", String.valueOf(Idsanpham));
            }
        }

        back_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        if (MainActivity.manggiohang.size() > 0) {
            count_gio_hang_search.setText(String.valueOf(MainActivity.manggiohang.size()));
        }

        shopping_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    mMainActivity.goToLogin();
                }
            }
        });

        sanPhamAdapter = new SanPhamAdapter(dataSearch(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_search.setAdapter(sanPhamAdapter);
        rcv_search.setHasFixedSize(true);
        rcv_search.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view;
    }

    public ArrayList<SanPham> dataSearch() {
        listDataSearch = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datasearch, new Response.Listener<String>() {
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
                            listDataSearch.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham,status));
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
                param.put("idloaisanpham", String.valueOf(Idloaisanpham));
                param.put("idsanpham", String.valueOf(Idsanpham));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return listDataSearch;
    }

    public void anhXa(View view) {
        rcv_search = view.findViewById(R.id.rcv_search);
        back_search = view.findViewById(R.id.back_search);
        shopping_search = view.findViewById(R.id.shopping_search);
        count_gio_hang_search = view.findViewById(R.id.count_gio_hang_search);
    }
}