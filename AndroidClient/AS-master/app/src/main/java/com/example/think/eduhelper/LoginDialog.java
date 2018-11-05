package com.example.think.eduhelper;

import android.accounts.Account;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.eduhelper.Chat.core.login.LoginPresenter;
import com.example.think.eduhelper.HttpClient.HttpCallback;
import com.example.think.eduhelper.HttpClient.HttpHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.example.think.eduhelper.UserLoginDB.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;
import java.util.List;

public class LoginDialog extends DialogFragment {
    private EditText name,password;
    private static int SIGN_IN_REQUEST_CODE = 1;
    private ProgressDialog mProgressDialog;

    FirebaseAuth mAuth;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.login_dialog,null));
        // get the firebase auth
        mAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dialogView = (Dialog) dialog;
                name = dialogView.findViewById(R.id.editText_LoginName);
                password = dialogView.findViewById(R.id.editText_LoginPassword);
                String userName = name.getText().toString();
                String userPassword = password.getText().toString();


                // using fire base to skip the backend
                if(mAuth.getCurrentUser() == null){
                    mAuth.signInWithEmailAndPassword(userName, userPassword)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>(){
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        // FirebaseUser user = mAuth.getCurrentUser();
                                        // startActivity(new Intent(getContext(),AccountPage.class));
                                        Toast.makeText(getActivity(), "Authentication successfully.", Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    /*List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.PhoneBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());
                    startActivityForResult( AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),SIGN_IN_REQUEST_CODE); */
                }else{
                    Toast.makeText(getActivity(), "Welcome " +FirebaseAuth.getInstance().getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),AccountPage.class));
                }
                // backend do not edit
                /*Log.d("name",name.getText().toString());
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", userName);
                params.put("userPassword", userPassword);
                JSONObject parameter = new JSONObject((params));
                //Start OKHTTP REQUEST
                HttpHelper.getInstance().postData(parameter, "login", new HttpCallback() {
                    @Override
                    public void postDataCallback(String Data) {
                    }

                });*/
                name.setText("");
                password.setText("");

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Return to the main page", Toast.LENGTH_SHORT).show();

            }
        });
        return builder.create();
    }

    public Boolean ifMatch(String name, String password){
        List<User> users= MainActivity.userInfoDatabase.userDao().getUsers();

        for(User user:users){
            if (user.getName().equals(name)){
                if (user.getPassword().equals(password)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
