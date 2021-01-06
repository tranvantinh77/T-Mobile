package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.GioHang;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;


public class ChiTietSPFragment extends Fragment {
    public static final String TAG = ChiTietSPFragment.class.getName();
    ImageView back_ct_sp, img_chi_tiet_sp, img_sp_btsheet, shopping_ctsp;
    TextView ten_chi_tiet_sp, gia_chi_tiet_sp, thong_so_sp, mo_ta_sp, ten_sp_btsheet, gia_sp_btsheet;
    Button btn_them_gio_hang, btn_minus, btn_so_luong, btn_plus, btn_order_or_buynow;

    MainActivity mMainActivity;
    LinearLayout linearLayout;

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_s_p, container, false);

       anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        //back
        back_ct_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    Toast.makeText(getContext(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
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

        // thêm vào giỏ hàng
        btn_them_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottmSheetDialodTheme);

                final View bottomSheet = LayoutInflater.from(getContext()).inflate(
                        R.layout.bottom_sheet_add_cart, linearLayout);

                // ánh xạ bottom sheet
                btn_minus = bottomSheet.findViewById(R.id.btn_minus);
                btn_so_luong = bottomSheet.findViewById(R.id.btn_so_luong);
                btn_plus = bottomSheet.findViewById(R.id.btn_plus);
                img_sp_btsheet = bottomSheet.findViewById(R.id.img_sp_btsheet);
                ten_sp_btsheet = bottomSheet.findViewById(R.id.ten_sp_btsheet);
                gia_sp_btsheet = bottomSheet.findViewById(R.id.gia_sp_btsheet);
                btn_order_or_buynow = bottomSheet.findViewById(R.id.btn_order_or_buynow);

                // set thông tin sản phẩm
                ten_sp_btsheet.setText(ten_chi_tiet_sp.getText());
                gia_sp_btsheet.setText(gia_chi_tiet_sp.getText());
                Picasso.get()
                        .load(Hinhanhchitiet)
                        .into(img_sp_btsheet);

                // chuyển sang giỏ hàng
                btn_order_or_buynow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MainActivity.manggiohang.size() > 0) {
                            int sl = Integer.parseInt(btn_so_luong.getText().toString());
                            boolean exists = false; for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                                if (MainActivity.manggiohang.get(i).getIdsp() == id) {
                                    MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                                    MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                                    exists = true;
                                }
                            }
                            if (exists == false) {
                                int soluong = Integer.parseInt(btn_so_luong.getText().toString());
                                long giaMoi = soluong * Giachitiet;
                                MainActivity.manggiohang.add(new GioHang(id,Tenchitiet,giaMoi,Hinhanhchitiet,soluong));
                            }
                        } else {
                            int soluong = Integer.parseInt(btn_so_luong.getText().toString());
                            long giaMoi = soluong * Giachitiet;
                            MainActivity.manggiohang.add(new GioHang(id,Tenchitiet,giaMoi,Hinhanhchitiet,soluong)); }
                        mMainActivity.goToGioHang();
                        bottomSheetDialog.dismiss();
                    }
                });
                // giảm số lượng
                btn_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int sl1 = Integer.parseInt(btn_so_luong.getText().toString());
                        int slmoi = Integer.parseInt(btn_so_luong.getText().toString());
                        btn_so_luong.setText(slmoi + "");

                        if (slmoi <= 1) {
                            btn_plus.setEnabled(true);
                            btn_minus.setEnabled(false);
                            btn_so_luong.setText(slmoi + "");
                        } else {
                            btn_plus.setEnabled(true);
                            btn_minus.setEnabled(true);
                            slmoi -= 1;
                            btn_so_luong.setText(slmoi + "");
                        }
                    }
                });

                // tăng số lượng
                btn_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int sl1 = Integer.parseInt(btn_so_luong.getText().toString());
                        int slmoi = Integer.parseInt(btn_so_luong.getText().toString());
                        slmoi += 1;
                        btn_so_luong.setText(slmoi + "");
                        btn_minus.setEnabled(true);
                        btn_so_luong.setText(slmoi + "");
                    }
                });

                bottomSheetDialog.setContentView(bottomSheet);
                bottomSheetDialog.show();
            }
        });


        return view;
    }

    public void anhXa(View view) {
        back_ct_sp = view.findViewById(R.id.back_ct_sp);
        shopping_ctsp = view.findViewById(R.id.shopping_ctsp);
        img_chi_tiet_sp = view.findViewById(R.id.img_chi_tiet_sp);
        ten_chi_tiet_sp = view.findViewById(R.id.ten_chi_tiet_sp);
        gia_chi_tiet_sp = view.findViewById(R.id.gia_chi_tiet_sp);
        thong_so_sp = view.findViewById(R.id.thong_so_sp);
        mo_ta_sp = view.findViewById(R.id.mo_ta_sp);
        btn_them_gio_hang = view.findViewById(R.id.btn_them_gio_hang);
        mMainActivity = (MainActivity) getActivity();
        linearLayout = view.findViewById(R.id.sheet);
    }
}