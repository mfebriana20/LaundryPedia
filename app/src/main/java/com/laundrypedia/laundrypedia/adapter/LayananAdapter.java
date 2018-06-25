package com.laundrypedia.laundrypedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.model.Layanan;

import java.util.ArrayList;
import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.LayananViewHolder> {

    private ArrayList<Layanan> dataList;

    public LayananAdapter(ArrayList<Layanan> dataList) {
        this.dataList = dataList;
    }

    @Override
    public LayananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cust_select_service, parent, false);
        return new LayananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LayananViewHolder holder, int position) {
        holder.txtNamaLayanan.setText(dataList.get(position).getNamaLayanan());
        holder.txtDesc.setText(dataList.get(position).getDescription());
        holder.txtItemPrice.setText(dataList.get(position).getItemPrice());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class LayananViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNamaLayanan, txtDesc, txtItemPrice;

        public LayananViewHolder(View itemView) {
            super(itemView);
            txtNamaLayanan = (TextView) itemView.findViewById(R.id.tvNamaLayanan);
            txtDesc = (TextView) itemView.findViewById(R.id.tvDescription);
            txtItemPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
