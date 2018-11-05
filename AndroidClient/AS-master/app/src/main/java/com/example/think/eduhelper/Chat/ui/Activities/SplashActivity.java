package com.example.think.eduhelper.Chat.ui.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.think.eduhelper.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    private Button bt_show_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bt_show_user = findViewById(R.id.bt_show_user);
        bt_show_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),FirebaseAuth.getInstance().getCurrentUser().getEmail() +" came here once！",Toast.LENGTH_LONG).show();
            }
        });
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                // check if user is already logged in or not
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    // if logged in redirect the user to user listing activity
                    UserListingActivity.startActivity(getApplicationContext());

                } else {
                    // otherwise redirect the user to login activity
                    // LoginActivity.startIntent(SplashActivity.this);
                }
                finish();
            }
        };

        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }*/
}
