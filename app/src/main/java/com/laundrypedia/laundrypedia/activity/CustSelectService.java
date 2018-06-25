package com.laundrypedia.laundrypedia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.adapter.LayananAdapter;
import com.laundrypedia.laundrypedia.model.Layanan;

import java.util.ArrayList;

public class CustSelectService extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LayananAdapter adapter;
    private ArrayList<Layanan> layananArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_select_service);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.rvLayanan);

        adapter = new LayananAdapter(layananArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CustSelectService.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        layananArrayList = new ArrayList<>();
        layananArrayList.add(new Layanan("Cuci Kering", "4000", "Cuci kering tanpa setrika"));
        layananArrayList.add(new Layanan("Cuci Basah", "2000", "Cuci masih basah tanpa setrika"));
        layananArrayList.add(new Layanan("Setrika", "3000", "Setrika "));
        layananArrayList.add(new Layanan("Cuci Setrika", "5000", "3 hari jadi"));
    }
}
