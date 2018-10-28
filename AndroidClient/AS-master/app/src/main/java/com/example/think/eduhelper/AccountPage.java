package com.example.think.eduhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountPage extends AppCompatActivity implements View.OnClickListener {
    private Button bt_profile;
    private Button bt_seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        bt_profile = findViewById(R.id.bt_profile);
        bt_profile.setOnClickListener(this);

        bt_seek = findViewById(R.id.bt_seekOrhelp);
        bt_seek.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_profile:
                startActivity(new Intent(this, ProfileSetting.class));
                break;
            case R.id.bt_seekOrhelp:
                startActivity(new Intent(this,TaskPageActivity.class));
        }
    }

}
