package com.vantinh.projectmobile.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.Model.ThuongHieu;
import com.vantinh.projectmobile.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    private Context context;
    private ArrayList<SanPham> arraydt;
    private IClickItemListener mIClickItemListener;


    public interface IClickItemListener {
        void onClickItem(SanPham sanPham);
    }

    public SanPhamAdapter(ArrayList<SanPham> arraydt, IClickItemListener mIClickItemListener) {
        this.arraydt = arraydt;
        this.mIClickItemListener = mIClickItemListener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final SanPham sanPham = arraydt.get(position);
        holder.ten_san_pham.setText(sanPham.getTen());
        holder.ten_san_pham.setMaxLines(2);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.gia_san_pham.setText("Giá : "+ decimalFormat.format(sanPham.getGia())+ " Đ");
        holder.gia_san_pham.setGravity(Gravity.BOTTOM);
        Picasso.get()
                .load(sanPham.getHinhanh())
                .fit()
                .into(holder.img_san_pham);

        holder.car_san_pham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemListener.onClickItem(sanPham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arraydt.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView img_san_pham;
        TextView ten_san_pham, gia_san_pham;
        MaterialCardView car_san_pham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            img_san_pham = itemView.findViewById(R.id.img_san_pham);
            ten_san_pham = itemView.findViewById(R.id.ten_san_pham);
            gia_san_pham = itemView.findViewById(R.id.gia_san_pham);
            car_san_pham = itemView.findViewById(R.id.car_san_pham);

        }
    }

}

