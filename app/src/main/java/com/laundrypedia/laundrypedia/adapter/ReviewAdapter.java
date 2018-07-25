package com.laundrypedia.laundrypedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.model.ReviewModel;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{
    private ArrayList<ReviewModel> review;

    public ReviewAdapter(ArrayList<ReviewModel> review) {
        this.review = review;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return (review == null) ? 0 : review.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CustName, Comment, ReviewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            CustName=(TextView) itemView.findViewById(R.id.tvCustName);
            Comment=(TextView)itemView.findViewById(R.id.tvComment);
            ReviewDate=(TextView)itemView.findViewById(R.id.tvReviewDate);

        }
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {
        holder.CustName.setText(review.get(position).getIdCust());
        holder.Comment.setText(review.get(position).getMessage());
        holder.ReviewDate.setText(review.get(position).getReviewDate());

    }
}
