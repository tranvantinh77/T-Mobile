package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;

public class RegisterFragment extends Fragment {
    public static final String TAG = RegisterFragment.class.getName();
    ImageView back_register;
    TextInputEditText username_register, email_register, phonenumber_register,
            password_register, enterpass_register;
    Button btn_register;
    View view;

    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        // ánh xạ
        back_register = view.findViewById(R.id.back_register);
        username_register = view.findViewById(R.id.username_register);
        email_register = view.findViewById(R.id.email_register);
        phonenumber_register = view.findViewById(R.id.phonenumber_register);
        password_register = view.findViewById(R.id.password_register);
        enterpass_register = view.findViewById(R.id.enterpass_register);
        btn_register = view.findViewById(R.id.btn_register);


        back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }
}