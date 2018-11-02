package com.example.think.eduhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.HttpClient.HttpCallback;
import com.example.think.eduhelper.HttpClient.HttpHelper;
import com.example.think.eduhelper.UserLoginDB.User;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText name, email, password;
    private Button bt_register;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bt_register = findViewById(R.id.bt_register);
        name = findViewById(R.id.signupname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        toolbar = findViewById(R.id.default_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void register(View view) {
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();
        String responseData;

        if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please  complete information", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userName", userName);
            params.put("userPassword", userPassword);
            params.put("userEmail", userEmail);
            JSONObject parameter = new JSONObject((params));

            //Start OKHTTP REQUEST
            HttpHelper.getInstance().postData(parameter, "register", new HttpCallback() {
                @Override
                public void postDataCallback(String Data) {
                    //Check if the user exists
                    final String jsonResponse = Data.replaceAll("[^0-9]","");
                    runOnUiThread(new Runnable() {
                        @Override
                        //Check if the username or email exits
                       public void run() {
                            if (jsonResponse.equals("6000")){
                                Toast.makeText(SignUpActivity.this, "Register successfully! Please login", Toast.LENGTH_LONG).show();
                                name.setText("");
                                email.setText("");
                                password.setText("");
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }
                            else if (jsonResponse.equals("6001")) {
                                Toast.makeText(SignUpActivity.this, "The username exists.", Toast.LENGTH_LONG).show();
                            }
                            else if (jsonResponse.equals("6002")) {
                                Toast.makeText(SignUpActivity.this, "The email exists.", Toast.LENGTH_LONG).show();
                            }
                            else if (jsonResponse.equals("6003")) {
                                Toast.makeText(SignUpActivity.this, "Oops System is busy. Please try again!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Please try again!", Toast.LENGTH_LONG).show();
                            }
                       }
                    });
                }
            });
        }
    }
}
