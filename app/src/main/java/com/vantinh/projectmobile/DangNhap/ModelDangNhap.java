package com.vantinh.projectmobile.DangNhap;


import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFacebook() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public void HuyTokenTracker() {
        accessTokenTracker.stopTracking();
    }

    public GoogleApiClient LayGoogleApiClient(Context context, int id, GoogleApiClient.OnConnectionFailedListener listener) {
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity)context, id, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
//để search thử
        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGG(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            return opr.get();
        } else {
            return null;
        }

    }

}
