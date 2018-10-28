package com.example.think.eduhelper;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.think.eduhelper.PostDB.PostDatabase;
import com.example.think.eduhelper.PostDB.PostInfo;
import com.example.think.eduhelper.UserLoginDB.UserInfoDatabase;

public class MainActivity extends AppCompatActivity {
    public static UserInfoDatabase userInfoDatabase;
    public static PostDatabase postDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfoDatabase = Room.databaseBuilder(getApplicationContext(), UserInfoDatabase.class, "userdb").allowMainThreadQueries().build();
        postDatabase =Room.databaseBuilder(getApplicationContext(),PostDatabase.class,"postdb").allowMainThreadQueries().build();

        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!= null){
                return ;
            }
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, loginFragment,null);
            fragmentTransaction.commit();
        }

    }
}
