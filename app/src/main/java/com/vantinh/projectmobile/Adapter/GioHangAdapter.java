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
        gia_san_pham_gh.setText(decimalFormat.format(arraygiohang.get(i).getGiasp())+" Đ");
        Log.d("test", String.valueOf(arraygiohang.size()));
        Log.d("test",arraygiohang.get(i).getHinhanhsp());
        Picasso.get().load(arraygiohang.get(i).getHinhanhsp()).into(img_san_pham_gh);
        btn_so_luong_gh.setText(arraygiohang.get(i).getSoluongsp()+"");

        return view;
    }
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
