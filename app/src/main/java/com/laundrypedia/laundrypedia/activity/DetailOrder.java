package com.laundrypedia.laundrypedia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.helper.DateUtils;
import com.laundrypedia.laundrypedia.model.LayananModel;
import com.laundrypedia.laundrypedia.model.OrderRequest;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetailOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ArrayList<LayananModel> layananSelecteds;
    private Gson gson = new Gson();
    private EditText etDatePickUp, etTimePickUp, etLocPickUp, etAddNotePickUp;
    private EditText etDateDeliv, etTimeDeliv, etLocDeliv, etAddNoteDeliv;
    private Button btnSubmit;

    private String strDatePickup = "";
    private String strDateDelivery = "";
    TimePickerDialog tpd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order);

        etDatePickUp = findViewById(R.id.etDatePickUp);
        etTimePickUp = findViewById(R.id.etTimePickUp);
        etLocPickUp = findViewById(R.id.etLocPickUp);
        etAddNotePickUp = findViewById(R.id.etAddNotePickUp);

        etDateDeliv = findViewById(R.id.etDateDeliv);
        etTimeDeliv = findViewById(R.id.etTimeDeliv);
        etLocDeliv = findViewById(R.id.etLocDeliv);
        etAddNoteDeliv = findViewById(R.id.etAddNoteDeliv);
        btnSubmit = findViewById(R.id.btnSubmit);


        final String itemJson = getIntent().getStringExtra("layananSelected");


        etDatePickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickup(strDatePickup).show(getFragmentManager(), "pickup");
            }
        });

        etDateDeliv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDelivery(strDatePickup, strDateDelivery).show(getFragmentManager(), "delivery");
            }
        });

        etTimePickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker().show(getFragmentManager(), "pickup");
            }
        });

        etTimeDeliv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker().show(getFragmentManager(), "delivery");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datePickup = etDatePickUp.getText().toString();
                String timePickup = etTimePickUp.getText().toString();
                String locPickup = etLocPickUp.getText().toString();
                String notePickup = etAddNotePickUp.getText().toString();

                String dateDeliv = etDateDeliv.getText().toString();
                String timeDeliv = etTimeDeliv.getText().toString();
                String locDeliv = etLocDeliv.getText().toString();
                String noteDeliv = etAddNoteDeliv.getText().toString();

                OrderRequest item = new OrderRequest();
                item.setDatePickup(datePickup);
                item.setTimePickup(timePickup);
                item.setLocationPickup(locPickup);
                item.setNotePickup(notePickup);
                item.setDateDelivery(dateDeliv);
                item.setTimeDelivery(timeDeliv);
                item.setLocationDelivery(locDeliv);
                item.setNoteDelivery(noteDeliv);
                item.setLayananModels(itemJson);

                Intent intent = new Intent(DetailOrder.this, ConfirmationOrderActivity.class);
                intent.putExtra("order", new Gson().toJson(item));
                startActivity(intent);

            }
        });


    }

    private DatePickerDialog showDatePickup(String strDateStart) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd;

        if (strDateStart.isEmpty()) {
            dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
        } else {
            Date date = DateUtils.stringtoDate(strDateStart, DateUtils.LONG_DATE_FORMAT);
            int year = Integer.parseInt(DateUtils.dateToString(date, "yyyy"));
            int month = Integer.parseInt(DateUtils.dateToString(date, "MM")) - 1;
            int day = Integer.parseInt(DateUtils.dateToString(date, "dd"));
            dpd = DatePickerDialog.newInstance(
                    this,
                    year,
                    month,
                    day
            );
        }

        Calendar calMinDate = Calendar.getInstance();
        dpd.setMinDate(calMinDate);

        return dpd;
    }

    private DatePickerDialog showDateDelivery(String strDateStart, String strDateEnd) {
        Date minDate = DateUtils.stringtoDate(strDateStart, DateUtils.LONG_DATE_FORMAT);
        Calendar calMin = Calendar.getInstance();
        calMin.setTime(minDate);


        DatePickerDialog dpd;

        if (strDateEnd.isEmpty()) {
            dpd = DatePickerDialog.newInstance(
                    this,
                    calMin.get(Calendar.YEAR),
                    calMin.get(Calendar.MONTH),
                    calMin.get(Calendar.DAY_OF_MONTH)
            );
        } else {
            Date date = DateUtils.stringtoDate(strDateEnd, DateUtils.LONG_DATE_FORMAT);
            int year = Integer.parseInt(DateUtils.dateToString(date, "yyyy"));
            int month = Integer.parseInt(DateUtils.dateToString(date, "MM")) - 1;
            int day = Integer.parseInt(DateUtils.dateToString(date, "dd"));
            dpd = DatePickerDialog.newInstance(
                    this,
                    year,
                    month,
                    day
            );
        }

        dpd.setMinDate(calMin);
        return dpd;
    }

    private TimePickerDialog showTimePicker() {
        Calendar now = Calendar.getInstance();
        if (tpd == null) {
            tpd = TimePickerDialog.newInstance(
                    this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );
        } else {
            tpd.initialize(
                    this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND),
                    true
            );
        }
        return tpd;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String days, month;
        if (dayOfMonth > 10) {
            days = String.valueOf(dayOfMonth);
        } else {
            days = "0" + dayOfMonth;
        }

        if (monthOfYear + 1 > 10) {
            month = String.valueOf(monthOfYear + 1);
        } else {
            month = "0" + (monthOfYear + 1);
        }
        String strDate = year + "-" + month + "-" + days;

        if (view.getTag().equals("pickup")) {
            strDatePickup = strDate;
            etDatePickUp.setText(strDate);
        } else {
            strDateDelivery = strDate;
            etDateDeliv.setText(strDate);
        }


    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondString = second < 10 ? "0" + second : "" + second;
        String time = hourString + ":" + minuteString + ":" + secondString;
        if (view.getTag().equals("pickup")) {
            etTimePickUp.setText(time);
        } else {
            etTimeDeliv.setText(time);
        }
    }
}
