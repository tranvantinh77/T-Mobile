package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vantinh.projectmobile.Adapter.GioHangAdapter;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.GioHang;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class GioHangFragment extends Fragment {
    public static final String TAG = GioHangFragment.class.getName();
    ImageView back_gio_hang;
    ListView lv_gio_hang;
    TextView txt_thong_bao, tong_tien;
    Button btn_mua_ngay;

    GioHangAdapter gioHangAdapter;
    private MainActivity mMainActivity;
    View view;


    public GioHangFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        // ánh xạ
        back_gio_hang = view.findViewById(R.id.back_gio_hang);
        lv_gio_hang = view.findViewById(R.id.lv_gio_hang);
        txt_thong_bao = view.findViewById(R.id.txt_thong_bao);
        tong_tien = view.findViewById(R.id.tong_tien);
        btn_mua_ngay = view.findViewById(R.id.btn_mua_ngay);

        gioHangAdapter = new GioHangAdapter(getContext(), MainActivity.manggiohang);
        lv_gio_hang.setAdapter(gioHangAdapter);


        mMainActivity = (MainActivity) getActivity();
        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        //back
       back_gio_hang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (getFragmentManager() != null) {
                   getFragmentManager().popBackStack();
               }
           }
       });

        if (MainActivity.manggiohang.size() <= 0) {
            gioHangAdapter.notifyDataSetChanged();
            txt_thong_bao.setVisibility(View.VISIBLE);
            lv_gio_hang.setVisibility(View.INVISIBLE);
        } else {
            gioHangAdapter.notifyDataSetChanged();
            txt_thong_bao.setVisibility(View.INVISIBLE);
            lv_gio_hang.setVisibility(View.VISIBLE);
        }


        return view;
    }


}
