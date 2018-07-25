package com.laundrypedia.laundrypedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.model.LayananModel;

import java.util.ArrayList;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.ViewHolder> {

    private ArrayList<LayananModel> layanan;
    private BtnClickListener mClickListener = null;

    public LayananAdapter(ArrayList<LayananModel> layanan, BtnClickListener listener) {
        this.layanan = layanan;
        mClickListener = listener;
    }

    @Override
    public LayananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_services, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LayananAdapter.ViewHolder holder, int position) {
        final LayananModel item = layanan.get(position);

        holder.nama.setText(layanan.get(position).getNamaLayanan());
        holder.itemPrice.setText(layanan.get(position).getItemPrice());
        holder.desc.setText(layanan.get(position).getDescription());
//        Picasso.with(context)
//                .load(layanan.get(position).getPicture()).resize(50, 50)
//                .into(holder.pic);

        holder.pic.setTag(position);
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onBtnClick((Integer) v.getTag());
                }
            }
        });
        holder.tvQty.setText(String.valueOf(item.getQty()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = item.getQty() + 1;
                item.setQty(newQuantity);
                notifyDataSetChanged();
            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getQty() > 0) {
                    int newQuantity = item.getQty() - 1;
                    item.setQty(newQuantity);
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return layanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, itemPrice, desc, tvQty;
        ImageView pic;
        Button btnRemove, btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tvSeviceName);
            itemPrice = itemView.findViewById(R.id.tvItemPrice);
            desc = itemView.findViewById(R.id.tvServiceDescription);
            pic = itemView.findViewById(R.id.ServiceLogo);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            tvQty = itemView.findViewById(R.id.tvQty);

        }
    }

    public interface BtnClickListener {
        void onBtnClick(int position);
    }
}
