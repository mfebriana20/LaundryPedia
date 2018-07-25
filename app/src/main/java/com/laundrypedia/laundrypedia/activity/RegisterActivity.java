package com.laundrypedia.laundrypedia.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laundrypedia.laundrypedia.R;
import com.laundrypedia.laundrypedia.helper.SQLiteHandlerCust;
import com.laundrypedia.laundrypedia.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private TextView btnLogin;
    private EditText etFullname, etEmail, etPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandlerCust db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = (EditText) findViewById(R.id.etFullname);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (TextView) findViewById(R.id.btnLogin);

        //progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        //session manager
        session = new SessionManager(getApplicationContext());

        //sqlite db handler
        db = new SQLiteHandlerCust(getApplicationContext());

        //check if user already logged in. take to main act
        if (session.isLoggedIn()) {
            Intent intent = new Intent(RegisterActivity.this, CustHome.class);
            startActivity(intent);
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = etFullname.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!fullname.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    registUser(fullname, email,password);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your detail", Toast.LENGTH_LONG).show();
                }
            }
        });

        //link to login screen
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LaundryLoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void registUser(final String fullname,final String email,final String password) {
        //tag used to cancel req
        String tag_string_request = "req_register";

        pDialog.setMessage("Registering....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER_CUST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jobj = new JSONObject(response);
                    boolean error = jobj.getBoolean("error");
                    if (!error) {
                        //user succesfully stored in mysql
                        //now store the user in sqlite
                        String uid = jobj.getString("uid");

                        JSONObject user = jobj.getJSONObject("user");
                        String fullname = user.getString("fulllname");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        //inserting row in user table
                        db.addUser(fullname, email, uid, created_at);

                        Toast.makeText(getApplicationContext(), "User successfuly registered. Try again!", Toast.LENGTH_LONG).show();

                        //laundh login act
                        Intent intent = new Intent(RegisterActivity.this, CustLoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //error occured in regist. get error msg
                        String errMsg = jobj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration error: "+ error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map <String, String> getParams(){
                //posting params to regist url
                Map<String, String> params = new HashMap<String, String>();
                params.put("fullname", fullname);
                params.put("email", email);
                params.put("password", password);


                return params;
            }
        };

        //adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_request);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
