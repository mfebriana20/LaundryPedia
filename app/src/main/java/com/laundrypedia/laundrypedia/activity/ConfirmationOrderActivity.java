package com.laundrypedia.laundrypedia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.helper.Log;
import com.laundrypedia.laundrypedia.model.LayananModel;
import com.laundrypedia.laundrypedia.model.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationOrderActivity extends AppCompatActivity {

    OrderRequest orderRequest;
    Gson gson = new Gson();
    ArrayList<LayananModel> layananModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order_activity);

        String itemJson = getIntent().getStringExtra("order");
        orderRequest = gson.fromJson(itemJson, OrderRequest.class);
        Log.e("item", itemJson);

        layananModels = gson.fromJson(orderRequest.getLayananModels(),
                new TypeToken<List<LayananModel>>() {
                }.getType());


    }
}
