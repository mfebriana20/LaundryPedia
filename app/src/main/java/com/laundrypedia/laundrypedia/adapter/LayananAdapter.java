package com.laundrypedia.laundrypedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                if(mClickListener!=null){
                    mClickListener.onBtnClick((Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (layanan  == null) ? 0 : layanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, itemPrice, desc;
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.tvSeviceName);
            itemPrice = (TextView) itemView.findViewById(R.id.tvItemPrice);
            desc = (TextView) itemView.findViewById(R.id.tvServiceDescription);
            pic = (ImageView) itemView.findViewById(R.id.ServiceLogo);
        }
    }

    public interface BtnClickListener {
        public abstract void onBtnClick(int position);
    }
}
