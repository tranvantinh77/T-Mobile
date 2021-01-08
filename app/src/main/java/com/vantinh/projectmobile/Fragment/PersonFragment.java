package com.vantinh.projectmobile.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vantinh.projectmobile.DangNhap.ModelDangNhap;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = PersonFragment.class.getName();
    public static TextView doi_mat_khau,dang_xuat,textID,xin_chao;
    ImageView img_forget_pass, img_logout;
    Button btnLogin;
    private MainActivity mMainActivity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor,editor2;
    String FULLNAME_KEY = "fullname";
    String tennguoidung = "";
    AccessToken accessToken;
    ModelDangNhap modelDangNhap;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;

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
        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(),1,this);

        laytennguoidungfb();
        laytennguoidunggoogle();//đợi chạy thử

        if (textID.getText() != "") {
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
            img_forget_pass.setVisibility(View.VISIBLE);
            img_logout.setVisibility(View.VISIBLE);
        } else {
            doi_mat_khau.setVisibility(View.INVISIBLE);
            dang_xuat.setVisibility(View.INVISIBLE);
            btnLogin.setVisibility(View.VISIBLE);
            xin_chao.setVisibility(View.INVISIBLE);
            textID.setVisibility(View.INVISIBLE);
            img_forget_pass.setVisibility(View.INVISIBLE);
            img_logout.setVisibility(View.INVISIBLE);
        }

        dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editora = sharedPreferences.edit();
                editora.clear();
                editora.apply();
                LoginManager.getInstance().logOut();

                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                }
                mMainActivity.goToDXuat();
            }
        });


        return view;
    }

    private void laytennguoidungfb() {
        accessToken = modelDangNhap.LayTokenFacebook();
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        textID.setText(tennguoidung);
                        doi_mat_khau.setVisibility(View.VISIBLE);
                        dang_xuat.setVisibility(View.VISIBLE);
                        btnLogin.setVisibility(View.INVISIBLE);
                        xin_chao.setVisibility(View.VISIBLE);
                        textID.setVisibility(View.VISIBLE);
                        img_forget_pass.setVisibility(View.VISIBLE);
                        img_logout.setVisibility(View.VISIBLE);
                        editor = sharedPreferences.edit();
                        editor.putString(FULLNAME_KEY,textID.getText().toString().trim());
                        editor.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields","name");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

    }

    private void laytennguoidunggoogle() {
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGG(mGoogleApiClient);
        if (googleSignInResult != null) {
            textID.setText(googleSignInResult.getSignInAccount().getDisplayName());
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
            img_forget_pass.setVisibility(View.VISIBLE);
            img_logout.setVisibility(View.VISIBLE);
            editor = sharedPreferences.edit();
            editor.putString(FULLNAME_KEY,textID.getText().toString().trim());
            editor.apply();
        }
    }

    public void receviceDataFromLogin(String fullname) {
        textID.setText(fullname);
        if (textID.getText() != "") {
            doi_mat_khau.setVisibility(View.VISIBLE);
            dang_xuat.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            xin_chao.setVisibility(View.VISIBLE);
            textID.setVisibility(View.VISIBLE);
            img_forget_pass.setVisibility(View.VISIBLE);
            img_logout.setVisibility(View.VISIBLE);
            editor = sharedPreferences.edit();
            editor.putString(FULLNAME_KEY,textID.getText().toString().trim());
            editor.apply();
        }
    }

    public void anhXa(View view) {
        mMainActivity = (MainActivity) getActivity();
        btnLogin = view.findViewById(R.id.btnLogin);
        textID = view.findViewById(R.id.textID);
        xin_chao = view.findViewById(R.id.xin_chao);
        doi_mat_khau = view.findViewById(R.id.doi_mat_khau);
        dang_xuat = view.findViewById(R.id.dang_xuat);
        img_forget_pass = view.findViewById(R.id.img_forget_pass);
        img_logout = view.findViewById(R.id.img_logout);

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
}
