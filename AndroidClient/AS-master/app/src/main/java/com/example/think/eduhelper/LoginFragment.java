package com.example.think.eduhelper;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.Chat.core.login.LoginContract;
import com.example.think.eduhelper.Chat.core.login.LoginPresenter;
import com.example.think.eduhelper.Chat.ui.Activities.SignUpActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener,LoginContract.View{
    private Button bt_openLogin;
    private Button bt_openSignup;
    private LoginPresenter mLoginPresenter;
    private EditText email,password;
    private static int SIGN_IN_REQUEST_CODE = 1;
    private ProgressDialog mProgressDialog;



    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        bindViews(view);
        init();
        return view;
    }



    @Override
    public void onClick(View v) {
        // click
        switch (v.getId()){
            case R.id.bt_openLoginDialog:
                onClickLogin();
                break;

            case R.id.bt_openSignupDialog:
                startActivity(new Intent(getContext(), SignUpActivity.class));
                break;
        }
    }


    public void bindViews(View view){
        email = view.findViewById(R.id.editText_LoginEmail);
        password = view.findViewById(R.id.editText_LoginPassword);
        bt_openLogin = view.findViewById(R.id.bt_openLoginDialog);
        bt_openSignup = view.findViewById(R.id.bt_openSignupDialog);
        mLoginPresenter = new LoginPresenter(this);
    }

    public void init(){
        bt_openLogin.setOnClickListener(this);
        bt_openSignup.setOnClickListener(this);




        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

    }


    public void onClickLogin(){
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        mLoginPresenter.login(getActivity(), userEmail,userPassword);
        mProgressDialog.show();
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), AccountPage.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}