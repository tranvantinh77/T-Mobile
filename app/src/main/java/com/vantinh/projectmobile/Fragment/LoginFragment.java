package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getName();
    ImageView back_login;
    TextInputEditText email_login, password_login;
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

       anhXa(view);

        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        new_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToRegister();
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToForget();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });

        return view;
    }

    public void login() {
        final String email = email_login.getText().toString().trim();
        final String password = password_login.getText().toString().trim();

        if (!email.equals("") && !password.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;

                    PutData putData = new PutData("https://tranvantinhit77nul.000webhostapp.com/server/login.php", "POST", field, data);

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                Toast.makeText(getContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            }
                            else {
                                Toast.makeText(getContext(),"Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    public void anhXa(View view) {
        back_login = view.findViewById(R.id.back_login);
        email_login = view.findViewById(R.id.email_login);
        password_login = view.findViewById(R.id.password_login);
        new_username = view.findViewById(R.id.new_username);
        forget_password = view.findViewById(R.id.forget_password);
        btn_login = view.findViewById(R.id.btn_login);
    }
}