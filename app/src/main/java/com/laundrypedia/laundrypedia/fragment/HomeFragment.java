package com.laundrypedia.laundrypedia.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.activity.LaundryDetail;
import com.laundrypedia.laundrypedia.model.LaundryList;
import com.laundrypedia.laundrypedia.adapter.LaundryAdapter;
import com.laundrypedia.laundrypedia.helper.MenuClickRecyclerview;
import com.laundrypedia.laundrypedia.model.LaundryModel;
import com.laundrypedia.laundrypedia.service.api.ApiClient;
import com.laundrypedia.laundrypedia.service.api.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<LaundryModel> binatu = new ArrayList();
    private LaundryAdapter laundryAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);

        Intent intent = new Intent(getActivity().getBaseContext(), DescriptionFragment.class);
        intent.putExtra("id_binatu", 0);
        getActivity().startActivity(intent);



        MenuClickRecyclerview.addTo(recyclerView).setOnItemClickListener(new MenuClickRecyclerview.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), LaundryDetail .class);
                startActivity(intent);

            }
        });

        return view;

    }

    private void initViews(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.rvLaundry);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        ApiInterface service = ApiClient.getClient();
        Call<LaundryList> call = service.getJSONLaundry("http://laundrypedia.store/cust/selectlaundry.php");
        call.enqueue(new Callback<LaundryList>() {
            @Override
            public void onResponse(Call<LaundryList> call, Response<LaundryList> response) {
                if(response.isSuccessful()) {
                    LaundryList laundryList = response.body();
                    binatu = new ArrayList<>(Arrays.asList(laundryList.getBinatu()));
                    laundryAdapter  =  new LaundryAdapter(binatu);
                    recyclerView.setAdapter(laundryAdapter);

                } else {
                    Toast.makeText(getActivity(),"Error load data!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LaundryList> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });

    }
}
