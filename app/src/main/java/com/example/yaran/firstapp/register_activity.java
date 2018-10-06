package com.example.yaran.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {
    databaseHelper db;
    EditText e_name, e_email, e_pswd, e_cpswd;
    Button b_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        db = new databaseHelper(this);

        e_name =  (EditText)findViewById(R.id.name);
        e_email = (EditText)findViewById(R.id.email);
        e_pswd = (EditText)findViewById(R.id.password);
        e_cpswd = (EditText)findViewById(R.id.cpassword);
        b_register = (Button)findViewById(R.id.register);

        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String s_name = e_name.getText().toString();
                String s_email = e_email.getText().toString();
                String s_password = e_pswd.getText().toString();
                String s_cpswd = e_cpswd.getText().toString();

                if (s_name.equals("")||s_email.equals("")||s_password.equals("")||s_cpswd.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s_password.equals(s_cpswd)) {
                        Boolean checkMail =db.check_email(s_email);
                        if(checkMail) {
                            Boolean ret = db.insert(s_email, s_password);
                            if (ret) {
                                Toast.makeText(getApplicationContext(), "Register Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
