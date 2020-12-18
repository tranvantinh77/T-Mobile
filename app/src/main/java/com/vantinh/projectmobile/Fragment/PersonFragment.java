package com.vantinh.projectmobile.Fragment;

import android.content.Intent;
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
    TextView textLogin;
    private MainActivity mMainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        mMainActivity = (MainActivity) getActivity();
        //Hooks
        textLogin = view.findViewById(R.id.textLogin);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLogin();

            }
        });

        return view;
    }
}
