package com.vantinh.projectmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vantinh.projectmobile.R;
import com.vantinh.projectmobile.Model.ThuongHieu;

import java.util.List;

public class ThuongHieuAdapter extends RecyclerView.Adapter<ThuongHieuAdapter.THViewHolder>{
    private Context context;

    private List<ThuongHieu> mThuongHieus;
    private IClickItemListener mIClickItemListener;

    public interface IClickItemListener {
        void onClickItemTH(ThuongHieu thuongHieu);
    }

    public ThuongHieuAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<ThuongHieu> list, IClickItemListener listener) {
        this.mThuongHieus = list;
        this.mIClickItemListener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public THViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuong_hieu, parent, false);
        return new THViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull THViewHolder holder, int position) {
        final ThuongHieu thuongHieu = mThuongHieus.get(position);
        if (thuongHieu == null) {
            return ;
        }

        holder.img_thuong_hieu.setImageResource(thuongHieu.getResourceId());

        holder.img_thuong_hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemListener.onClickItemTH(thuongHieu);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mThuongHieus != null) {
            return mThuongHieus.size();
        }
        return 0;
    }

    public class THViewHolder extends RecyclerView.ViewHolder {

        ImageView img_thuong_hieu;

        public THViewHolder(@NonNull View itemView) {
            super(itemView);

            img_thuong_hieu = itemView.findViewById(R.id.img_thuong_hieu);
        }
    }

}
