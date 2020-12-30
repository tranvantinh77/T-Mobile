package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;



public class PersonFragment extends Fragment {
    public static final String TAG = PersonFragment.class.getName();
    TextView textLogin,doi_mat_khau,dang_xuat;
    private MainActivity mMainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLogin();

            }
        });

        return view;
    }

    public void anhXa(View view) {
        mMainActivity = (MainActivity) getActivity();
        textLogin = view.findViewById(R.id.textLogin);
        doi_mat_khau = view.findViewById(R.id.doi_mat_khau);
        dang_xuat = view.findViewById(R.id.dang_xuat);

    }
}
