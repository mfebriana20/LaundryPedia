package com.laundrypedia.laundrypedia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.activity.DetailOrder;
import com.laundrypedia.laundrypedia.adapter.LayananAdapter;
import com.laundrypedia.laundrypedia.helper.MenuClickRecyclerview;
import com.laundrypedia.laundrypedia.model.LaundryModel;
import com.laundrypedia.laundrypedia.model.LayananList;
import com.laundrypedia.laundrypedia.model.LayananModel;
import com.laundrypedia.laundrypedia.service.api.ApiClient;
import com.laundrypedia.laundrypedia.service.api.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<LayananModel> layanan;
    private LayananAdapter layananAdapter;

    public ServicesFragment() {
        // Required empty public constructor
    }

    private void loadJSON() {
//        int idBinatu =this.getArguments().getString("id_binatu").toString();

        String idBinatu = getActivity().getIntent().getStringExtra("id_binatu");
        Log.e("idBinatu", idBinatu);
        ApiInterface service = ApiClient.getClient();

        Call<LayananList> call = service.getJSONLayanan("http://laundrypedia.store/cust/selectlayanan.php?id_binatu=" + idBinatu);
        call.enqueue(new Callback<LayananList>() {
            @Override
            public void onResponse(@NonNull Call<LayananList> call, Response<LayananList> response) {
                if (response.isSuccessful()) {
                    LayananList layananList = response.body();
                    layanan = new ArrayList<>(Arrays.asList(layananList.getLayanan())); //wes entuk data

                    Log.e("size", "" + layanan.size());
                    layananAdapter = new LayananAdapter(layanan, new LayananAdapter.BtnClickListener() {
                        @Override
                        public void onBtnClick(int position) {
                            Toast.makeText(getContext(), layanan.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView.setAdapter(layananAdapter);

                } else {
                    Toast.makeText(getActivity(), "Error load data!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LayananList> call, Throwable t) {
                Toast.makeText(getActivity(), "internet error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //    IdBinatu = getActivity().getIntent().getIntExtra("id_binatu",0);

        recyclerView = view.findViewById(R.id.rvLayanan);
        Button btnOrder = view.findViewById(R.id.btnOrder);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        // Inflate the layout for this fragment
        if (savedInstanceState == null) {
            loadJSON();
        }


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceSelected().size() > 0) {
                    Intent intent = new Intent(getActivity(), DetailOrder.class);
                    intent.putExtra("layananSelected", new Gson().toJson(serviceSelected()));
                    startActivity(intent);
                }
            }
        });

    }

    private ArrayList<LayananModel> serviceSelected() {
        ArrayList<LayananModel> items = new ArrayList<>();
        for (LayananModel item : layanan) {
            if (item.getQty() > 0) {
                items.add(item);
            }
        }
        return items;
    }

}
