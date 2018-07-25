package com.laundrypedia.laundrypedia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundrypedia.laundrypedia.R;

public class DescriptionFragment extends Fragment {
    TextView Store_name, Rating, Description;
            String idBinatu;

    public DescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ctrl + alt + o // hapus import rak penting
        //ctrl + alt + l ngerapike kode
        View returnView = inflater.inflate(R.layout.fragment_description, container, false);
        idBinatu = getActivity().getIntent().getStringExtra("id_binatu");

        Store_name = returnView.findViewById(R.id.tvLaundryName);
        Rating = returnView.findViewById(R.id.tvRating);
        Description = returnView.findViewById(R.id.tvDescription);

        Store_name.setText(getActivity().getIntent().getStringExtra("store_name"));
        Rating.setText(getActivity().getIntent().getStringExtra("rating"));
        Description.setText(getActivity().getIntent().getStringExtra("description"));


        // Inflate the layout for this fragment
        return returnView;


    }
}
