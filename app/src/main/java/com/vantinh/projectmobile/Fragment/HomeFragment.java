package com.vantinh.projectmobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.vantinh.projectmobile.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       //Hooks
        imageSlider = view.findViewById(R.id.slider);


        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/mi-10-ultra-1.jpg"));
        slideModels.add(new SlideModel("https://cdn.mobilecity.vn/mobilecity-vn/images/2020/06/xiaomi-redmi-10x.jpg"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/11/banner/800-300-800x300-6.png"));
        slideModels.add(new SlideModel("https://cdn.cellphones.com.vn/media/ltsoft/promotion/MI_NOTE_10.png"));
        slideModels.add(new SlideModel("https://mistore.com.vn/wp-content/uploads/2020/08/k30-ultra-1.jpg"));
        imageSlider.setImageList(slideModels,true);
        return view;
    }

}
