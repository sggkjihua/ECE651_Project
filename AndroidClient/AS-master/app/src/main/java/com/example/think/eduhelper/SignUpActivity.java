package com.example.think.eduhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.HttpClient.HttpHelper;
import com.example.think.eduhelper.UserLoginDB.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        password =findViewById(R.id.signuppassword);
        toolbar = findViewById(R.id.default_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    public void register(View view) {
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();

        //User user = new User();
        if (checkIfExists(userName)){
            Toast.makeText(this,"User Name already exists",Toast.LENGTH_SHORT).show();
        }
        else{
            if (userName.isEmpty()||userEmail.isEmpty()||userPassword.isEmpty()){
                Toast.makeText(this,"Please  complete information",Toast.LENGTH_SHORT).show();
            }else{
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", userName);
                params.put("userPassword", userPassword);
                params.put("userEmail", userEmail);
                JSONObject parameter = new JSONObject((params));
                //Start OKHTTP REQUEST
                HttpHelper.getInstance().postData(parameter);
                Toast.makeText(this,"Register successfully! Please login",Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                password.setText("");
                startActivity(new Intent(this,MainActivity.class));
            }
        }

    }

    public Boolean checkIfExists(String name){
        // check if the user name has already been registered
        List<User> users= MainActivity.userInfoDatabase.userDao().getUsers();

        for(User user:users){
            if (user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
