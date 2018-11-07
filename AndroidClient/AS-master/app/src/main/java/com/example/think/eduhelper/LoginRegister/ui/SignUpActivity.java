package com.example.think.eduhelper.LoginRegister.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.Chat.ui.Activities.UserListingActivity;
import com.example.think.eduhelper.LoginRegister.core.registration.RegisterContract;
import com.example.think.eduhelper.LoginRegister.core.registration.RegisterPresenter;
import com.example.think.eduhelper.Chat.core.users.add.AddUserContract;
import com.example.think.eduhelper.Chat.core.users.add.AddUserPresenter;
import com.example.think.eduhelper.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, RegisterContract.View, AddUserContract.View {
    private EditText name, email, password;
    private Button bt_register;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindView();
        init();
    }

    private void bindView(){
        // firebase auth initialized
        mAuth = FirebaseAuth.getInstance();

        // find the component in current view
        bt_register = findViewById(R.id.bt_register);
        name = findViewById(R.id.signupname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        toolbar = findViewById(R.id.default_toolBar);

        // enable the backward
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void init() {
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

        bt_register.setOnClickListener(this);
    }

    public void register(View view) {
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();

        if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            //Log.d(, "createUserWithEmail:success");
            Toast.makeText(this, "Please complete information", Toast.LENGTH_SHORT).show();
        } else {
            mProgressDialog.show();
            mRegisterPresenter.register(this, userEmail, userPassword);
            //mRegisterPresenter.register(SignUpActivity.this, userEmail, userPassword);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_register:{
                // click the register button and call register function
                register(v);
                break;
            }
        }
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage(getString(R.string.adding_user_to_db));
        Toast.makeText(this, "Registration Successful! Welcome "+ firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(this, firebaseUser);
        // clear the input space
        name.setText("");
        email.setText("");
        password.setText("");
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage(getString(R.string.please_wait));
        Toast.makeText(this, "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        UserListingActivity.startActivity(this,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}




            /* backend work uncooment to cover
            /*
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
        }*/