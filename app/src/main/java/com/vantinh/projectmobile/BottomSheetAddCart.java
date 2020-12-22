package com.vantinh.projectmobile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;


public class BottomSheetAddCart extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;

    ImageView img_sp_btsheet;
    TextView ten_sp_btsheet, gia_sp_btsheet;
    Button btn_minus, btn_so_luong, btn_plus, btn_order_or_buynow;

    String Ten = "";
    Integer Gia = 0;
    String Hinhanh = "";
    String btn = "";


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.bottom_sheet_add_cart, container, false);
       // Ánh xạ
        img_sp_btsheet = view.findViewById(R.id.img_sp_btsheet);
        ten_sp_btsheet = view.findViewById(R.id.ten_sp_btsheet);
        gia_sp_btsheet = view.findViewById(R.id.gia_sp_btsheet);
        btn_minus = view.findViewById(R.id.btn_minus);
        btn_so_luong = view.findViewById(R.id.btn_so_luong);
        btn_plus = view.findViewById(R.id.btn_plus);
        btn_order_or_buynow = view.findViewById(R.id.btn_order_or_buynow);

        // nhận dữ liệu
        Bundle bundleReceive = getArguments();
        Ten = bundleReceive.getString("ten");
        Gia = bundleReceive.getInt("gia");
        Hinhanh = bundleReceive.getString("hinhanh");
        btn = bundleReceive.getString("btn_them_gio_hang");
        ten_sp_btsheet.setText(Ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia_sp_btsheet.setText(decimalFormat.format(Gia) + " Đ");
        Picasso.get()
                .load(Hinhanh)
                .into(img_sp_btsheet);
        btn_order_or_buynow.setText(btn);

       return view;
    }

    public interface BottomSheetListener {
        void onButtonClicked();
    }

}
