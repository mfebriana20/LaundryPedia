package com.laundrypedia.laundrypedia.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CustLoginActivity extends Activity {
    private static final String TAG = CustRegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail;
    private EditText etPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandlerCust db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        
        //progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        
        //sqlite db handler
        db = new SQLiteHandlerCust(getApplicationContext());
        
        //session manager
        session= new SessionManager(getApplicationContext());
        
        //chech if user already logged in or not
        if (session.isLoggedIn()){
            //user is already logged in . take to main act
            Intent intent = new Intent(CustLoginActivity.this, CustRegisterActivity.class);
            startActivity(intent);
            finish();
        }
        
        //login btn click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                //check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()){
                    //login user
                    checkLogin(email,password);
                } else {
                    //prompt user to enter credential
                    Toast.makeText(getApplicationContext(),"Insert your email and password!",  Toast.LENGTH_LONG).show(); 
                }
            }
        });
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(CustLoginActivity.this,CustRegisterActivity.class);
                startActivity(ii);
                finish();
            }
        });
    }

    private void checkLogin(final String email,final String password) {
        //tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN_CUST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    boolean error = jobj.getBoolean("error");

                    //check for error node in json
                    if (!error) {
                        //user successfully logged in
                        //create logging session
                        session.setLogin(true);

                        //now store the user in sqlite
                        String uid = jobj.getString("uid");

                        JSONObject user = jobj.getJSONObject("user");
                        String fullname = user.getString("fullname");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        //inserting row in user table
                        db.addUser(fullname, email, uid, created_at);

                        //launch main act
                        Intent intent = new Intent(CustLoginActivity.this, CustHome.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //error login. get error message
                        String errMsg = jobj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    //json error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login error: " +error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                //posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        //adding req to req queue
        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);
    }

    private void hideDialog() {
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
}

