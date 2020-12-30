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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.util.Map;


public class SanPhamSaleFragment extends Fragment {
    public static final String TAG = SanPhamSaleFragment.class.getName();
    ImageView back_sale, shopping_gg;
    RecyclerView rcv_sale;
    private MainActivity mMainActivity;
    SaleAdapter saleAdapter;
    private int status = 1;
    View view;

    public SanPhamSaleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_san_pham_sale, container, false);

        anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);


        back_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_gg.setOnClickListener(new View.OnClickListener() {
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
        rcv_sale.setAdapter(saleAdapter);
        rcv_sale.setHasFixedSize(true);
        rcv_sale.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view;
    }

    private ArrayList<SanPhamSale> getDataSale() {
        final ArrayList<SanPhamSale> sanPhamSales = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Log.d("a","aaaa");
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
                param.put("status", String.valueOf(status));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return sanPhamSales;
    }

    public void anhXa(View view) {
        back_sale = view.findViewById(R.id.back_sale);
        shopping_gg = view.findViewById(R.id.shopping_gg);
        rcv_sale = view.findViewById(R.id.rcv_sale);
        mMainActivity = (MainActivity) getActivity();
    }
}