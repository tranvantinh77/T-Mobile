package com.vantinh.projectmobile.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanFragment extends Fragment {
    public static final String TAG = ThanhToanFragment.class.getName();
    TextInputEditText ten_khach_hang, email, so_dien_thoai, dia_chi, ghi_chu;
    Button btn_hủy, btn_xac_nhan;
    View view;
    MainActivity mMainActivity;

    public ThanhToanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        mMainActivity = (MainActivity) getActivity();
        anhXa(view);

        btn_hủy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        btn_xac_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tenkhachhang = ten_khach_hang.getText().toString().trim();
                final String emaildonhang = email.getText().toString().trim();
                final String sodienthoai = so_dien_thoai.getText().toString().trim();
                final String diachi = dia_chi.getText().toString().trim();
                final String ghichu = ghi_chu.getText().toString().trim();

                if (tenkhachhang.length() > 0 && emaildonhang.length() > 0 && sodienthoai.length() > 0 && diachi.length() > 0 && ghichu.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.insertdonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang", madonhang);
                            RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.chitietdonhang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("")) {
                                        MainActivity.manggiohang.clear();
                                        Toast.makeText(getContext(), "Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                        mMainActivity.goToHome();
                                    } else {
                                        Toast.makeText(getContext(), "Đặt hàng thất bại",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    JSONArray jsonArray = new JSONArray();
                                    for (int i = 0 ; i < MainActivity.manggiohang.size() ; i++) {
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang",madonhang);
                                            jsonObject.put("masanpham",MainActivity.manggiohang.get(i).idsp);
                                            jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).tensp);
                                            jsonObject.put("hinhanhsanpham",MainActivity.manggiohang.get(i).hinhanhsp);
                                            jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).giasp);
                                            jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).soluongsp);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                    }
                                    HashMap<String, String> param = new HashMap<String, String>();
                                    param.put("json", jsonArray.toString());

                                    return param;
                                }
                            };
                            requestQueue1.add(stringRequest1);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("tenkhachhang", tenkhachhang);
                            param.put("email", emaildonhang);
                            param.put("sodienthoai", sodienthoai);
                            param.put("diachi", diachi);
                            param.put("ghichu", ghichu);
                            return param;
                        }
                    };
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(getContext(), "Kiểm tra lại dữ liệu",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private void anhXa(View view) {
        ten_khach_hang = view.findViewById(R.id.ten_khach_hang);
        email = view.findViewById(R.id.email);
        so_dien_thoai = view.findViewById(R.id.so_dien_thoai);
        dia_chi = view.findViewById(R.id.dia_chi);
        ghi_chu = view.findViewById(R.id.ghi_chu);
        btn_hủy = view.findViewById(R.id.btn_hủy);
        btn_xac_nhan = view.findViewById(R.id.btn_xac_nhan);

    }

}