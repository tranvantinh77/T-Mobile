package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;

public class CategoryFragment extends Fragment {
    MaterialCardView card_phone, card_laptop, card_phukien;

    private MainActivity mMainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mMainActivity = (MainActivity) getActivity();

        // Ánh xạ
        card_phone = view.findViewById(R.id.card_phone);
        card_laptop = view.findViewById(R.id.card_laptop);
        card_phukien = view.findViewById(R.id.card_phukien);


        card_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToDienThoai();
            }
        });

        card_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLaptop();
            }
        });

        card_phukien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToPhuKien();
            }
        });

        return view;
    }
}
