package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;


public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getName();
    ImageView back_login;
    TextInputEditText username_login, password_login;
    TextView new_username, forget_password;
    Button btn_login;
    View view;

    private MainActivity mMainActivity;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
       mMainActivity = (MainActivity) getActivity();

        // ánh xạ
        back_login = view.findViewById(R.id.back_login);
        username_login = view.findViewById(R.id.username_login);
        password_login = view.findViewById(R.id.password_login);
        new_username = view.findViewById(R.id.new_username);
        forget_password = view.findViewById(R.id.forget_password);
        btn_login = view.findViewById(R.id.btn_login);


        new_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToRegister();
            }
        });

        back_login.setOnClickListener(new View.OnClickListener() {
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