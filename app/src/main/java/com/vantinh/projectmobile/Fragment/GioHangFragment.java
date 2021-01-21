package com.vantinh.projectmobile.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vantinh.projectmobile.Adapter.GioHangAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;


public class GioHangFragment extends Fragment {
    public static final String TAG = GioHangFragment.class.getName();
    ImageView back_gio_hang;
    ListView lv_gio_hang;
    public static TextView txt_thong_bao, tong_tien;
    public static Button btn_mua_ngay;

    public static GioHangAdapter gioHangAdapter;
    private MainActivity mMainActivity;
    View view;


    public GioHangFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        anhXa(view);

        gioHangAdapter = new GioHangAdapter((MainActivity) getContext(), MainActivity.manggiohang);
        lv_gio_hang.setAdapter(gioHangAdapter);
        eventUtil();

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
        //btn mua ngay
        if (MainActivity.manggiohang.size() > 0) {
            btn_mua_ngay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMainActivity.goToThanhToan();
                }
            });
        } else {
            btn_mua_ngay.setEnabled(false);
            btn_mua_ngay.setBackgroundColor(Color.DKGRAY);
        }

        return view;
    }

    // tong tien
    public static void eventUtil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFomat = new DecimalFormat("###,###,###");
        tong_tien.setText(decimalFomat.format(tongtien) + " Đ");
    }

    public void anhXa(View view) {
        back_gio_hang = view.findViewById(R.id.back_gio_hang);
        lv_gio_hang = view.findViewById(R.id.lv_gio_hang);
        txt_thong_bao = view.findViewById(R.id.txt_thong_bao);
        tong_tien = view.findViewById(R.id.tong_tien);
        btn_mua_ngay = view.findViewById(R.id.btn_mua_ngay);
    }
}
