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


public class ForgetFragment extends Fragment {
    public static final String TAG = ForgetFragment.class.getName();
    ImageView back_forget;
    TextInputEditText email_forget;
    Button btn_forget;

    View view;

    public ForgetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forget, container, false);

        //Ánh xạ
        back_forget = view.findViewById(R.id.back_forget);
        email_forget = view.findViewById(R.id.email_forget);
        btn_forget = view.findViewById(R.id.btn_forget);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        // Back
        back_forget.setOnClickListener(new View.OnClickListener() {
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