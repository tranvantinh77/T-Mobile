package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.vantinh.projectmobile.MainActivity;
import com.vantinh.projectmobile.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    TextView xem_san_pham_sale, xem_dien_thoai, xem_laptop;
    RecyclerView rcv_san_pham_sale, rcv_dien_thoai_noibat, rcv_laptop_noibat;

    private MainActivity mMainActivity;
    View view;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

       // Ánh xạ
        imageSlider = view.findViewById(R.id.slider);
        xem_san_pham_sale = view.findViewById(R.id.xem_san_pham_sale);
        xem_dien_thoai = view.findViewById(R.id.xem_dien_thoai);
        xem_laptop = view.findViewById(R.id.xem_laptop);
        rcv_san_pham_sale = view.findViewById(R.id.rcv_san_pham_sale);
        rcv_dien_thoai_noibat = view.findViewById(R.id.rcv_dien_thoai_noibat);
        rcv_laptop_noibat = view.findViewById(R.id.rcv_laptop_noibat);
        mMainActivity = (MainActivity) getActivity();

        // slide
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/mi-10-ultra-1.jpg"));
        slideModels.add(new SlideModel("https://cdn.mobilecity.vn/mobilecity-vn/images/2020/06/xiaomi-redmi-10x.jpg"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/11/banner/800-300-800x300-6.png"));
        slideModels.add(new SlideModel("https://cdn.cellphones.com.vn/media/ltsoft/promotion/MI_NOTE_10.png"));
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/k30-ultra-1.jpg"));
        imageSlider.setImageList(slideModels,true);

        xem_san_pham_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToSale();
            }
        });

        xem_dien_thoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToDienThoai();
            }
        });

        xem_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.goToLaptop();
            }
        });

        return view;
    }

}
