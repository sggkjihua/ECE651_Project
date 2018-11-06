package com.example.think.eduhelper.Post.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.think.eduhelper.R;

public class PostActivity extends AppCompatActivity{
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        bindViews();
        init(savedInstanceState);

    }

    public void bindViews(){
        mToolbar = findViewById(R.id.toolbar_post);

    }

    public void init(Bundle savedInstanceState){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (findViewById(R.id.frame_layout_content_post)!=null){
            if (savedInstanceState!= null){
                return ;
            }
            PostFragment postFragment = new PostFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_content_post, postFragment,null);
            fragmentTransaction.commit();
        }
    }
}
