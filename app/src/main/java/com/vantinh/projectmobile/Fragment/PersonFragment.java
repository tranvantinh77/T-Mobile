package com.vantinh.projectmobile.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    public static TextView doi_mat_khau,dang_xuat,textID,xin_chao;
    Button btnLogin;
    private MainActivity mMainActivity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String FULLNAME_KEY = "fullname";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        anhXa(view);
        sharedPreferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        textID.setText(sharedPreferences.getString(FULLNAME_KEY,""));

        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLogin();
            }
        });
        if (textID.getText() != "") {
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
        } else {
            doi_mat_khau.setVisibility(View.INVISIBLE);
            dang_xuat.setVisibility(View.INVISIBLE);
            btnLogin.setVisibility(View.VISIBLE);
            xin_chao.setVisibility(View.INVISIBLE);
            textID.setVisibility(View.INVISIBLE);
        }

        dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editora = sharedPreferences.edit();
                editora.clear();
                editora.commit();
                mMainActivity.goToDXuat();
            }
        });

        return view;
    }

    public void receviceDataFromLogin(String fullname) {
        textID.setText(fullname);
        if (textID.getText() != "") {
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
            editor = sharedPreferences.edit();
            editor.putString(FULLNAME_KEY,textID.getText().toString().trim());
            editor.commit();
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
