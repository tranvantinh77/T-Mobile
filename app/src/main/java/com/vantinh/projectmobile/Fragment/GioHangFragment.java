package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;


public class GioHangFragment extends Fragment {
    public static final String TAG = GioHangFragment.class.getName();
    ImageView back_gio_hang;
    View view;

    public GioHangFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        // ánh xạ
        back_gio_hang = view.findViewById(R.id.back_gio_hang);


        MainActivity.bottomNavigationView.setVisibility(View.INVISIBLE);

        //back
       back_gio_hang.setOnClickListener(new View.OnClickListener() {
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
