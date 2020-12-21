package com.vantinh.projectmobile.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import com.vantinh.projectmobile.Model.SanPhamSale;
import com.vantinh.projectmobile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ItemSaleHolder> {
    private Context context;
    private ArrayList<SanPhamSale> arraysale;
    private IClickItemListener mIClickItemListener;


    public interface IClickItemListener {
        void onClickItem(SanPhamSale sanPhamSale);
    }

    public SaleAdapter(ArrayList<SanPhamSale> arraysale, IClickItemListener mIClickItemListener) {
        this.arraysale = arraysale;
        this.mIClickItemListener = mIClickItemListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemSaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham_sale, null);
        ItemSaleHolder itemHolder = new ItemSaleHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSaleHolder holder, int position) {
        final SanPhamSale sanPhamSale = arraysale.get(position);
        holder.ten_san_pham_sale.setText(sanPhamSale.getTen());
        holder.ten_san_pham_sale.setMaxLines(2);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.gia_sale.setText("Giá : "+ decimalFormat.format(sanPhamSale.getGiasale())+ " Đ");
        holder.gia_sale.setGravity(Gravity.BOTTOM);
        holder.gia_goc.setText(decimalFormat.format(sanPhamSale.getGia())+ " Đ");
        holder.gia_goc.setPaintFlags(holder.gia_goc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Picasso.get()
                .load(sanPhamSale.getHinhanh())
                .fit()
                .into(holder.img_san_pham_sale);

        holder.car_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemListener.onClickItem(sanPhamSale);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arraysale.size();
    }

    public class ItemSaleHolder extends RecyclerView.ViewHolder{
        ImageView img_san_pham_sale;
        TextView ten_san_pham_sale, gia_sale, gia_goc;
        MaterialCardView car_sale;

        public ItemSaleHolder(@NonNull View itemView) {
            super(itemView);

            img_san_pham_sale = itemView.findViewById(R.id.img_san_pham_sale);
            ten_san_pham_sale = itemView.findViewById(R.id.ten_san_pham_sale);
            gia_sale = itemView.findViewById(R.id.gia_sale);
            gia_goc = itemView.findViewById(R.id.gia_goc);
            car_sale = itemView.findViewById(R.id.car_sale);

        }
    }
}
