package com.laundrypedia.laundrypedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.adapter.ReviewAdapter;
import com.laundrypedia.laundrypedia.model.ReviewList;
import com.laundrypedia.laundrypedia.model.ReviewModel;
import com.laundrypedia.laundrypedia.service.api.ApiClient;
import com.laundrypedia.laundrypedia.service.api.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ReviewModel> review;
    private ReviewAdapter reviewAdapter;
    public String idBinatu;

    public ReviewFragment() {
        // Required empty public constructor
    }

    private void loadJSON() {

        String idBinatu= getActivity().getIntent().getStringExtra("id_binatu");
        ApiInterface service = ApiClient.getClient();

        Call<ReviewList> call = service.getJSONReview("http://laundrypedia.store/cust/selectreview.php?id_binatu="+idBinatu);
        call.enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(Call<ReviewList> call, Response<ReviewList> response) {
                if (response.isSuccessful()){
                    ReviewList reviewList = response.body();
                    review = new ArrayList<>(Arrays.asList(reviewList.getReview()));
                    reviewAdapter = new ReviewAdapter(review);
                    recyclerView.setAdapter(reviewAdapter);
                } else {
                    Toast.makeText(getActivity(), "Error load data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return   inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        idBinatu = getActivity().getIntent().getStringExtra("id_binatu");
//
        recyclerView = view.findViewById(R.id.rvReview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
//        //ReviewAdapter reviewAdapter = new ReviewAdapter(review,getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(reviewAdapter);

        // Inflate the layout for this fragment

        loadJSON();
    }
}
