package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.Adapter.ListPhotoAdapter;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.GioHang;
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


public class ChiTietSPFragment extends Fragment {
    public static final String TAG = ChiTietSPFragment.class.getName();
    ImageView back_ct_sp, img_sp_btsheet, shopping_ctsp;
    TextView ten_chi_tiet_sp, gia_chi_tiet_sp, thong_so_sp, mo_ta_sp, ten_sp_btsheet, gia_sp_btsheet,count_gio_hang;
    Button btn_them_gio_hang, btn_minus, btn_so_luong, btn_plus, btn_order_or_buynow;
    RecyclerView rcv_sp_lien_quandt,rcv_sp_lien_quanlt,rcv_sp_lien_quanpk;
    ViewPager view_pager;

    MainActivity mMainActivity;
    LinearLayout linearLayout;
    SanPhamAdapter sanPhamAdapter1;


    int id = 0;
    String Tenchitiet = "";
    Integer Giachitiet = 0;
    String Hinhanhchitiet = "";
    String Hinhanhchitiet2 = "";
    String Hinhanhchitiet3 = "";
    String Hinhanhchitiet4 = "";
    String Thongso = "";
    String Motachitiet = "";
    int Idsanpham = 0;
    int Idloaisanpham = 0;

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

    View view;

    public ChiTietSPFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_s_p, container, false);

       anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        if (MainActivity.manggiohang.size() > 0) {
            count_gio_hang.setText(String.valueOf(MainActivity.manggiohang.size()));
        }

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
                    mMainActivity.goToLogin();
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
                Hinhanhchitiet2 = sanPham.getHinhanh2();
                Hinhanhchitiet3 = sanPham.getHinhanh3();
                Hinhanhchitiet4 = sanPham.getHinhanh4();
                Thongso = sanPham.getThongsokithuat();
                Motachitiet = sanPham.getMota();
                Idsanpham = sanPham.getIDsanpham();
                Idloaisanpham = sanPham.getIDloaisanpham();


                ten_chi_tiet_sp.setText(Tenchitiet);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                gia_chi_tiet_sp.setText("Giá: " + decimalFormat.format(Giachitiet) + " Đ");
                thong_so_sp.setText(Thongso);
                mo_ta_sp.setText(Motachitiet);

                String[] image = new String[]{
                        Hinhanhchitiet,
                        Hinhanhchitiet2,
                        Hinhanhchitiet3,
                        Hinhanhchitiet4
                };
                ListPhotoAdapter listPhotoAdapter = new ListPhotoAdapter(getContext(),image);
                view_pager.setAdapter(listPhotoAdapter);


                Log.d("aaaa",Hinhanhchitiet2);

            }
        }

        // thêm vào giỏ hàng
        btn_them_gio_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {

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
                } else {
                    mMainActivity.goToLogin();
                }

            }
        });

            sanPhamAdapter1 = new SanPhamAdapter(getsplienquan(), new SanPhamAdapter.IClickItemListener() {
                @Override
                public void onClickItem(SanPham sanPham) {
                    mMainActivity.goToCTSP(sanPham);
                }
            });
            rcv_sp_lien_quanpk.setVisibility(View.GONE);
            rcv_sp_lien_quandt.setAdapter(sanPhamAdapter1);
            rcv_sp_lien_quandt.setHasFixedSize(true);
            rcv_sp_lien_quandt.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        return view;
    }

    private ArrayList<SanPham> getsplienquan() {
       final ArrayList<SanPham> splienquan = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.datathuonghieu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
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
                            splienquan.add(new SanPham(id,ten,hinhanh,hinhanh2, hinhanh3, hinhanh4,gia,thongsokithuat,mota,idloaisanpham,idsanpham));
                            sanPhamAdapter1.notifyDataSetChanged();
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
                param.put("idsanpham", String.valueOf(Idsanpham));
                param.put("idloaisanpham", String.valueOf(Idloaisanpham));
                return param;
            }
        };

        requestQueue.add(stringRequest);

        return splienquan;
    }

    public void anhXa(View view) {
        back_ct_sp = view.findViewById(R.id.back_ct_sp);
        shopping_ctsp = view.findViewById(R.id.shopping_ctsp);
        view_pager = view.findViewById(R.id.view_pager);
        ten_chi_tiet_sp = view.findViewById(R.id.ten_chi_tiet_sp);
        gia_chi_tiet_sp = view.findViewById(R.id.gia_chi_tiet_sp);
        thong_so_sp = view.findViewById(R.id.thong_so_sp);
        mo_ta_sp = view.findViewById(R.id.mo_ta_sp);
        count_gio_hang = view.findViewById(R.id.count_gio_hangct);
        btn_them_gio_hang = view.findViewById(R.id.btn_them_gio_hang);
        rcv_sp_lien_quandt = view.findViewById(R.id.rcv_sp_lien_quandt);
        rcv_sp_lien_quanlt = view.findViewById(R.id.rcv_sp_lien_quanlt);
        rcv_sp_lien_quanpk = view.findViewById(R.id.rcv_sp_lien_quanpk);
        mMainActivity = (MainActivity) getActivity();
        linearLayout = view.findViewById(R.id.sheet);
    }
}