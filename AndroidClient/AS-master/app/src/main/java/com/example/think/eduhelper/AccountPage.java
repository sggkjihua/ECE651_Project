package com.example.think.eduhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.think.eduhelper.Chat.ui.Activities.SplashActivity;
import com.example.think.eduhelper.Chat.ui.Activities.UserListingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AccountPage extends AppCompatActivity implements View.OnClickListener {
    private Button bt_profile;
    private Button bt_seek;
    private Button bt_missions;
    private Button bt_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        bt_profile = findViewById(R.id.bt_profile);
        bt_profile.setOnClickListener(this);

        bt_seek = findViewById(R.id.bt_seekOrhelp);
        bt_seek.setOnClickListener(this);

        bt_missions  = findViewById(R.id.bt_missions);
        bt_missions.setOnClickListener(this);

        bt_logout  = findViewById(R.id.bt_logOut);
        bt_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_profile:
                startActivity(new Intent(this, ProfileSetting.class));
                break;
            case R.id.bt_seekOrhelp:
                startActivity(new Intent(this,TaskPageActivity.class));
                break;
            case R.id.bt_missions:
                startActivity(new Intent(this,UserListingActivity.class));
                break;
            case R.id.bt_logOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

}
