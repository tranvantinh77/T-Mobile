package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;


public class ChiTietSPFragment extends Fragment {
    public static final String TAG = ChiTietSPFragment.class.getName();
    ImageView back_ct_sp, img_chi_tiet_sp;
    TextView ten_chi_tiet_sp, gia_chi_tiet_sp, thong_so_sp, mo_ta_sp;
    Button btn_them_gio_hang, btn_mua_ngay;

    int id = 0;
    String Tenchitiet = "";
    Integer Giachitiet = 0;
    String Hinhanhchitiet = "";
    String Thongso = "";
    String Motachitiet = "";
    int Idsanpham = 0;

    View view;
    public ChiTietSPFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_s_p, container, false);

        back_ct_sp = view.findViewById(R.id.back_ct_sp);
        img_chi_tiet_sp = view.findViewById(R.id.img_chi_tiet_sp);
        ten_chi_tiet_sp = view.findViewById(R.id.ten_chi_tiet_sp);
        gia_chi_tiet_sp = view.findViewById(R.id.gia_chi_tiet_sp);
        thong_so_sp = view.findViewById(R.id.thong_so_sp);
        mo_ta_sp = view.findViewById(R.id.mo_ta_sp);
        btn_them_gio_hang = view.findViewById(R.id.btn_them_gio_hang);
        btn_mua_ngay = view.findViewById(R.id.btn_mua_ngay);

        //back
        back_ct_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
                    getFragmentManager().popBackStack();
                }
            }
        });

        // nhận dữ liệu
        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            SanPham sanPham = (SanPham) bundleReceive.getSerializable("dien_thoai");
            if (sanPham != null) {
                id = sanPham.getID();
                Tenchitiet = sanPham.getTen();
                Giachitiet = sanPham.getGia();
                Hinhanhchitiet = sanPham.getHinhanh();
                Thongso = sanPham.getThongsokithuat();
                Motachitiet = sanPham.getMota();
                Idsanpham = sanPham.getIDsanpham();
                ten_chi_tiet_sp.setText(Tenchitiet);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                gia_chi_tiet_sp.setText("Giá: " + decimalFormat.format(Giachitiet) + " Đ");
                thong_so_sp.setText(Thongso);
                mo_ta_sp.setText(Motachitiet);
                Picasso.get()
                        .load(Hinhanhchitiet)
                        .into(img_chi_tiet_sp);

            }
        }



        return view;
    }
}