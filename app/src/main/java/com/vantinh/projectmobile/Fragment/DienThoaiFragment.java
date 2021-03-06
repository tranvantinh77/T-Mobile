package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.vantinh.projectmobile.Adapter.SanPhamAdapter;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.Adapter.ThuongHieuAdapter;

import java.util.ArrayList;
import java.util.List;

public class DienThoaiFragment extends Fragment {
    public static final String TAG = DienThoaiFragment.class.getName();
    ImageView back_dienthoai, shopping_dt;
    RecyclerView rcv_thuong_hieu, rcv_dien_thoai;
    TextView count_gio_hang;
    private MainActivity mMainActivity;
    View view;

    public DienThoaiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dien_thoai, container, false);

        anhXa(view);
        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        if (MainActivity.manggiohang.size() > 0) {
            count_gio_hang.setText(String.valueOf(MainActivity.manggiohang.size()));
        }

        // thuong hieu
        ThuongHieuAdapter thuongHieuAdapter = new ThuongHieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false);
        rcv_thuong_hieu.setLayoutManager(linearLayoutManager);

        // Chuyển thương hiệu
        thuongHieuAdapter.setData(getListData(), new ThuongHieuAdapter.IClickItemListener() {
            @Override
            public void onClickItemTH(ThuongHieu thuongHieu) {
                mMainActivity.goToThuongHieuDienThoai(thuongHieu);
            }
        });

        rcv_thuong_hieu.setAdapter(thuongHieuAdapter);

        // Back
        back_dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        shopping_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PersonFragment.textID.getText() != "") {
                    mMainActivity.goToGioHang();
                } else {
                    mMainActivity.goToLogin();
                }
            }
        });

         mMainActivity.sanPhamAdapter = new SanPhamAdapter(mMainActivity.dataDienThoai(), new SanPhamAdapter.IClickItemListener() {
            @Override
            public void onClickItem(SanPham sanPham) {
                mMainActivity.goToCTSP(sanPham);
            }
        });

        rcv_dien_thoai.setAdapter(mMainActivity.sanPhamAdapter);
        rcv_dien_thoai.setHasFixedSize(true);
        rcv_dien_thoai.setLayoutManager(new GridLayoutManager(getContext(),2));


        return view;
    }


    private List<ThuongHieu> getListData() {
        List<ThuongHieu> list = new ArrayList<>();

        list.add(new ThuongHieu(R.drawable.iphone, "Iphone",1001));
        list.add(new ThuongHieu(R.drawable.samsung, "Samsung",1002));
        list.add(new ThuongHieu(R.drawable.xiaomi, "Xiaomi",1003));
        list.add(new ThuongHieu(R.drawable.vsmart, "Vsmart",1004));
        list.add(new ThuongHieu(R.drawable.realme, "Realme",1005));
        list.add(new ThuongHieu(R.drawable.oppo, "Oppo",1006));

        return list;
    }

    public void anhXa(View view) {
        back_dienthoai = view.findViewById(R.id.back_dienthoai);
        shopping_dt = view.findViewById(R.id.shopping_dt);
        rcv_thuong_hieu = view.findViewById(R.id.rcv_thuong_hieu);
        count_gio_hang = view.findViewById(R.id.count_gio_hangdt);
        rcv_dien_thoai = view.findViewById(R.id.rcv_dien_thoai);
        mMainActivity = (MainActivity) getActivity();
    }
}