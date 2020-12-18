package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.R;


public class ChiTietSPFragment extends Fragment {
    public static final String TAG = ChiTietSPFragment.class.getName();
    ImageView back_ct_sp;
    TextView textct;

    View view;
    public ChiTietSPFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_s_p, container, false);

        back_ct_sp = view.findViewById(R.id.back_ct_sp);
        textct = view.findViewById(R.id.textct);

        back_ct_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
                    getFragmentManager().popBackStack();
                }
            }
        });

        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            SanPham sanPham = (SanPham) bundleReceive.get("dien_thoai");
            if (sanPham != null) {
                textct.setText(sanPham.getTen());
            }
        }



        return view;
    }
}