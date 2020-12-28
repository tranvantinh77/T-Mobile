package com.vantinh.projectmobile.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.Fragment.GioHangFragment;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.GioHang;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }


    @Override
    public int getCount() {
        if (arraygiohang != null) {
            return arraygiohang.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.item_gio_hang,null);

        final TextView ten_san_pham_gh,gia_san_pham_gh;
        ImageView img_san_pham_gh;
        final Button btn_minus_gh,btn_so_luong_gh,btn_plus_gh;

        ten_san_pham_gh=view.findViewById(R.id.ten_san_pham_gh);
        gia_san_pham_gh=view.findViewById(R.id.gia_san_pham_gh);
        img_san_pham_gh=view.findViewById(R.id.img_san_pham_gh);
        btn_minus_gh=view.findViewById(R.id.btn_minus_gh);
        btn_plus_gh=view.findViewById(R.id.btn_plus_gh);
        btn_so_luong_gh=view.findViewById(R.id.btn_so_luong_gh);

        ten_san_pham_gh.setText(arraygiohang.get(i).getTensp());
        final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        gia_san_pham_gh.setText("Giá : " + decimalFormat.format(arraygiohang.get(i).getGiasp())+" Đ");
        Picasso.get().load(arraygiohang.get(i).getHinhanhsp()).into(img_san_pham_gh);
        btn_so_luong_gh.setText(arraygiohang.get(i).getSoluongsp()+"");
        final int sl= Integer.parseInt(btn_so_luong_gh.getText().toString());
        final int slmoi=sl;

        // giảm số lượng
        btn_minus_gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl1 = Integer.parseInt(btn_so_luong_gh.getText().toString());
                int slmoi = Integer.parseInt(btn_so_luong_gh.getText().toString());

                if (slmoi <= 1) {
                    btn_plus_gh.setEnabled(true);
                    btn_minus_gh.setEnabled(false);
                    btn_so_luong_gh.setText(slmoi + "");
                } else {
                    btn_plus_gh.setEnabled(true);
                    btn_minus_gh.setEnabled(true);
                    slmoi -= 1;
                    btn_so_luong_gh.setText(slmoi + "");
                }

                btn_so_luong_gh.setText(slmoi + "");
                MainActivity.manggiohang.get(i).setSoluongsp(slmoi);
                MainActivity.manggiohang.get(i).setGiasp(MainActivity.manggiohang.get(i).getGiasp()*slmoi/(sl1));
                gia_san_pham_gh.setText(MainActivity.manggiohang.get(i).getGiasp()+"");
                long tonggiasp = 0;
                tonggiasp = MainActivity.manggiohang.get(i).getGiasp();
                long tongtien=0;
                for(int i=0;i<MainActivity.manggiohang.size();i++){
                    tongtien+=MainActivity.manggiohang.get(i).getGiasp();
                }
                GioHangFragment.tong_tien.setText(decimalFormat.format(tongtien)+" Đ");
                gia_san_pham_gh.setText("Giá : " + decimalFormat.format(tonggiasp)+" Đ");
            }
        });

        // tăng số lượng
        btn_plus_gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl1=Integer.parseInt(btn_so_luong_gh.getText().toString());
                int slmoi=Integer.parseInt(btn_so_luong_gh.getText().toString());
                slmoi+=1;
                btn_so_luong_gh.setText(slmoi+"");
                MainActivity.manggiohang.get(i).setSoluongsp(slmoi);
                MainActivity.manggiohang.get(i).setGiasp(MainActivity.manggiohang.get(i).getGiasp()*slmoi/(sl1));
                gia_san_pham_gh.setText(MainActivity.manggiohang.get(i).getGiasp()+"");
                long tonggiasp = 0;
                tonggiasp = MainActivity.manggiohang.get(i).getGiasp();
                long tongtien=0;
                for(int i=0;i<MainActivity.manggiohang.size();i++){
                    tongtien+=MainActivity.manggiohang.get(i).getGiasp();
                }
                GioHangFragment.tong_tien.setText(decimalFormat.format(tongtien)+" Đ");
                gia_san_pham_gh.setText("Giá : " + decimalFormat.format(tonggiasp)+" Đ");
                btn_minus_gh.setEnabled(true);
                btn_so_luong_gh.setText(slmoi + "");
            }
        });

        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
