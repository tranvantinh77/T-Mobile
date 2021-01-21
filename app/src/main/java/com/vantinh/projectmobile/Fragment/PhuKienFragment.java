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
    TextView count_gio_hang;
    MainActivity mMainActivity;

    View view;
    public PhuKienFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phu_kien, container, false);

        anhXa(view);

        if (MainActivity.manggiohang.size() > 0) {
            count_gio_hang.setText(String.valueOf(MainActivity.manggiohang.size()));
        }

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
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    mMainActivity.goToLogin();
                }
            }
        });

        mMainActivity.sanPhamAdapter = new SanPhamAdapter(mMainActivity.dataPhuKien(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });
        rcv_phu_hien.setAdapter(mMainActivity.sanPhamAdapter);
//        rcv_dien_thoai.setHasFixedSize(true);
        rcv_phu_hien.setLayoutManager(new GridLayoutManager(getContext(),2));


        return view;
    }

    public void anhXa(View view) {
        back_phukien = view.findViewById(R.id.back_phukien);
        shopping_pk = view.findViewById(R.id.shopping_pk);
        rcv_phu_hien = view.findViewById(R.id.rcv_phu_kien);
        count_gio_hang = view.findViewById(R.id.count_gio_hangpk);
        mMainActivity = (MainActivity) getActivity();
    }
}