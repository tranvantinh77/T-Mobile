package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterFragment extends Fragment {
    public static final String TAG = RegisterFragment.class.getName();
    ImageView back_register;
    TextInputEditText username_register, email_register, phonenumber_register, password_register;
    Button btn_register;
    MainActivity mMainActivity;
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
        btn_register = view.findViewById(R.id.btn_register);
        mMainActivity = (MainActivity) getActivity();

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);


        back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = username_register.getText().toString().trim();
                final String password = password_register.getText().toString().trim();
                final String email = email_register.getText().toString().trim();
                final String phone = phonenumber_register.getText().toString().trim();

                if (!fullname.equals("") && !password.equals("") && !email.equals("") && !phone.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "password";
                            field[2] = "email";
                            field[3] = "phone";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = password;
                            data[2] = email;
                            data[3] = phone;
                            PutData putData = new PutData("https://tranvantinhit77nul.000webhostapp.com/server/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")){
                                        Toast.makeText(getContext(),"Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        getFragmentManager().popBackStack();
                                        Log.d("a","ok");
                                    }
                                    else {
                                        Toast.makeText(getContext(),"Email tồn tại, vui lòng kiểm tra lại", Toast.LENGTH_LONG).show();
                                        Log.d("b","not");
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });

        return view;
    }
}