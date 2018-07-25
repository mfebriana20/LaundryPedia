package com.laundrypedia.laundrypedia.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.adapter.LaundryAdapter;
import com.laundrypedia.laundrypedia.model.LaundryList;
import com.laundrypedia.laundrypedia.model.LaundryModel;
import com.laundrypedia.laundrypedia.service.api.ApiClient;
import com.laundrypedia.laundrypedia.service.api.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public RecyclerView recyclerView;
    public ArrayList<LaundryModel> binatu;
    private LaundryAdapter laundryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Log.d("called","oke");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViews();


    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.rvLaundryHome);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        ApiInterface service = ApiClient.getClient();
       int a = getIntent().getIntExtra("id_binatu",0);
        Call<LaundryList> call = service.getJSONLaundry("http://laundrypedia.store/cust/selectHome.php");
        call.enqueue(new Callback<LaundryList>() {
            @Override
            public void onResponse(Call<LaundryList> call, Response<LaundryList> response) {
                if(response.isSuccessful()) {
                    LaundryList laundryList = response.body();
                    binatu = new ArrayList<>(Arrays.asList(laundryList.getBinatu()));
                    laundryAdapter  =  new LaundryAdapter(binatu);
                    recyclerView.setAdapter(laundryAdapter);

                } else {
                    Toast.makeText(getApplicationContext(),"Error load data!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LaundryList> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cust_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent= new Intent(getApplicationContext(),CustHome.class);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(getApplicationContext(), HistoryOrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_balance) {
            Intent intent = new Intent(getApplicationContext(), HistoryBalance.class);
            startActivity(intent);

        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "cs@laundrypedia.store", null));
            startActivity(Intent.createChooser(intent, "Customer Help"));
        } else if (id == R.id.nav_account) {
            Intent intent = new Intent(getApplicationContext(), EditProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            item.setChecked(true);
            startActivity(new Intent(getApplicationContext(), CustLoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
