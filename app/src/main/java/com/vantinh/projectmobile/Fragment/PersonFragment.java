package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;


public class PersonFragment extends Fragment {
    public static final String TAG = PersonFragment.class.getName();
    TextView doi_mat_khau,dang_xuat,textID,xin_chao;
    Button btnLogin;
    private MainActivity mMainActivity;
    public static String name ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLogin();

            }
        });

        doi_mat_khau.setVisibility(View.INVISIBLE);
        dang_xuat.setVisibility(View.INVISIBLE);
        xin_chao.setVisibility(View.INVISIBLE);


        return view;
    }

    public void receviceDataFromLogin(String fullname) {
        textID.setText(fullname);
        if (textID.getText() != null) {
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
            textID.setText(fullname);
        }
    }

    public void anhXa(View view) {
        mMainActivity = (MainActivity) getActivity();
        btnLogin = view.findViewById(R.id.btnLogin);
        textID = view.findViewById(R.id.textID);
        xin_chao = view.findViewById(R.id.xin_chao);
        doi_mat_khau = view.findViewById(R.id.doi_mat_khau);
        dang_xuat = view.findViewById(R.id.dang_xuat);

    }
}
