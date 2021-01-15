package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordFragment extends Fragment {
    public static final String TAG = ChangePasswordFragment.class.getName();
    ImageView back_change;
    TextInputEditText email_change;
    TextInputEditText oldpassword;
    TextInputEditText newpassword;
    TextInputEditText confirm_password;
    Button btn_change;

    MainActivity mMainActivity;

    View view;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_change_password, container, false);
        anhxa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);
        mMainActivity = (MainActivity) getActivity();

        // Back
        back_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return view;
    }

    public void changePassword() {
        final String email = email_change.getText().toString().trim();
        final String old_password = oldpassword.getText().toString().trim();
        final String new_password = newpassword.getText().toString().trim();

        if (!email.equals("") && !old_password.equals("") && !new_password.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[3];
                    field[0] = "email";
                    field[1] = "old_password";
                    field[2] = "new_password";
                    //Creating array for data
                    String[] data = new String[3];
                    data[0] = email;
                    data[1] = old_password;
                    data[2] = new_password;
                    PutData putData = new PutData(Server.changepassword, "POST", field, data);

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Change Password Success")) {
                                Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                if (getFragmentManager() != null) {
                                    PersonFragment.editor.clear();
                                    PersonFragment.editor.apply();
                                    getFragmentManager().popBackStack();
                                }
                            } else {
                                Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    public void anhxa(View view) {
        back_change = view.findViewById(R.id.back_change);
        email_change = view.findViewById(R.id.email_change);
        oldpassword = view.findViewById(R.id.old_password);
        newpassword = view.findViewById(R.id.new_password);
        confirm_password = view.findViewById(R.id.confirm_password);
        btn_change = view.findViewById(R.id.btn_change);


    }


}