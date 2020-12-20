package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;


public class SanPhamSaleFragment extends Fragment {
    ImageView back_sale;

    private MainActivity mMainActivity;
    View view;

    public SanPhamSaleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_san_pham_sale, container, false);

        // Ánh xạ
        back_sale = view.findViewById(R.id.back_sale);
        mMainActivity = (MainActivity) getActivity();


        back_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                    getFragmentManager().popBackStack();
                }
            }
        });


        return view;
    }
}