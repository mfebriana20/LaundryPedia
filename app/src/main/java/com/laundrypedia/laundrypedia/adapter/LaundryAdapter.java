package com.laundrypedia.laundrypedia.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.activity.LaundryDetail;
import com.laundrypedia.laundrypedia.model.LaundryModel;

import java.util.ArrayList;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.ViewHolder> {

    private ArrayList<LaundryModel> binatu;

    public LaundryAdapter(ArrayList<LaundryModel> binatu) {
        this.binatu = binatu;
    }


    //pertama
    @Override
    public LaundryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return (binatu  == null) ? 0 : binatu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storename, address;
        ImageView logo;

        public ViewHolder(View view) {
            super(view);
            storename = (TextView) view.findViewById(R.id.tvStoreName);
            address = (TextView) view.findViewById(R.id.tvAddress);
            logo = (ImageView) view.findViewById(R.id.Logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){

                        LaundryModel clickedDataItem = binatu.get(pos);

                        Intent intent = new Intent (itemView.getContext(), LaundryDetail.class);
                        intent.putExtra("id_binatu",clickedDataItem.getIdBinatu());
                        intent.putExtra("store_name",clickedDataItem.getStoreName());
                        intent.putExtra("address",clickedDataItem.getAddress());
                        intent.putExtra("rating",clickedDataItem.getRating());
                        intent.putExtra("description", clickedDataItem.getDescription());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final LaundryAdapter.ViewHolder holder, int position) {
        final LaundryModel current = binatu.get(position);
        holder.storename.setText(current.getStoreName());
        holder.address.setText(current.getAddress());

//        FragmentB fragmentB=new FragmentB();
//        Bundle bundle=new Bundle();
//        bundle.putString("NAME",current.name);
//        bundle.putString("JOB",current.job);
//        fragmentB.setArguments(bundle);
    }

}
