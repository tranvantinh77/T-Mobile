package com.vantinh.projectmobile.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.vantinh.projectmobile.DangNhap.ModelDangNhap;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.ultil.Server;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class LoginFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    public static final String TAG = LoginFragment.class.getName();
    ImageView back_login;
    TextInputEditText email_login, password_login;
    TextView new_username, forget_password;
    Button btn_login;
    CircleImageView login_facebook, login_google;
    private ISendDataListener mISendDataListener;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    ModelDangNhap modelDangNhap;

    public static int SIGN_IN_GOOGLE = 117;
    View view;

    int id = 0;
    String fullname = "";

    private MainActivity mMainActivity;

    public interface ISendDataListener {
        void senData(String fullname);
    }

    public LoginFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mISendDataListener = (ISendDataListener) getActivity();
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

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this, Arrays.asList("public_profile"));
                loginFacebook();
            }
        });

        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(), 1,this);

        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus,SIGN_IN_GOOGLE);
            }
        });


        return view;
    }

    private void loginFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
                Log.d("a","thanh cong");
            }

            @Override
            public void onCancel() {
                Log.d("a","that bai");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("a","loi");

            }
        });

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

                    PutData putData = new PutData(Server.login, "POST", field, data);

                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {

                                final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getfullname, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response != null) {
                                            try {
                                                JSONArray jsonArray = new JSONArray(response);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                    id = jsonObject.getInt("id");
                                                    fullname = jsonObject.getString("fullname");
                                                    Log.d("name",fullname);
                                                    mISendDataListener.senData(fullname);
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String, String> param = new HashMap<String, String>();
                                        param.put("email", email);
                                        return param;
                                    }
                                };

                                requestQueue.add(stringRequest);
                                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    if (getFragmentManager() != null) {
                                        getFragmentManager().popBackStack();
                                    }
                            } else {
                                Toast.makeText(getContext(), "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode == SIGN_IN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    public void anhXa(View view) {
        back_login = view.findViewById(R.id.back_login);
        email_login = view.findViewById(R.id.email_login);
        password_login = view.findViewById(R.id.password_login);
        new_username = view.findViewById(R.id.new_username);
        forget_password = view.findViewById(R.id.forget_password);
        btn_login = view.findViewById(R.id.btn_login);
        login_facebook = view.findViewById(R.id.login_facebook);
        login_google = view.findViewById(R.id.login_google);
    }

}