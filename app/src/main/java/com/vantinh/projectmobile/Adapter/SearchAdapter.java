package com.vantinh.projectmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.vantinh.projectmobile.Model.SanPham;
import com.vantinh.projectmobile.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> implements Filterable {
    private Context context;
    private ArrayList<SanPham> arraysanpham;
    private ArrayList<SanPham> arraysanphamOld;
    private IClickItemListener mIClickItemListener;

    public interface IClickItemListener {
        void onClickItem(SanPham sanPham);
    }

    public SearchAdapter(ArrayList<SanPham> arraysanpham, IClickItemListener mIClickItemListener) {
        this.arraysanpham = arraysanpham;
        this.arraysanphamOld = arraysanpham;
        this.mIClickItemListener = mIClickItemListener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, null);
        SearchHolder searchHolder = new SearchHolder(v);
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        final SanPham sanPham = arraysanpham.get(position);
        holder.ten_sp_search.setText(sanPham.getTen());
        holder.ten_sp_search.setMaxLines(2);
        holder.car_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemListener.onClickItem(sanPham);
            }
        });

    }

    @Override
    public int getItemCount() {
            return arraysanpham.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder {

        private TextView ten_sp_search;
        MaterialCardView car_search;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            ten_sp_search = itemView.findViewById(R.id.ten_sp_search);
            car_search = itemView.findViewById(R.id.car_search);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    arraysanpham = arraysanphamOld;
                } else {
                    ArrayList<SanPham> arrayList = new ArrayList<>();
                    for (SanPham sanPham : arraysanphamOld) {
                        if (sanPham.getTen().toLowerCase().contains(strSearch.toLowerCase())) {
                            arrayList.add(sanPham);
                        }
                    }

                    arraysanpham = arrayList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arraysanpham;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arraysanpham = (ArrayList<SanPham>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
