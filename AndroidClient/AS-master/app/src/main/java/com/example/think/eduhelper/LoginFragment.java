package com.example.think.eduhelper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button bt_openLogin;
    private Button bt_openSignup;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        bt_openLogin = view.findViewById(R.id.bt_openLoginDialog);
        bt_openLogin.setOnClickListener(this);

        bt_openSignup = view.findViewById(R.id.bt_openSignupDialog);
        bt_openSignup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        // click
        switch (v.getId()){
            case R.id.bt_openLoginDialog:
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.show(getFragmentManager(),"OpenLoginDialog");
                break;

            case R.id.bt_openSignupDialog:
                //SignUpDialog signUpDialog = new SignUpDialog();
                //signUpDialog.show(getFragmentManager(),"OpenSignUpDialog");
                startActivity(new Intent(getContext(), SignUpActivity.class));
                break;
        }

    }
}