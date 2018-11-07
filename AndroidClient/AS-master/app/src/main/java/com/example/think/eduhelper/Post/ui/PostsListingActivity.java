package com.example.think.eduhelper.Post.ui;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.think.eduhelper.R;

public class PostsListingActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ImageButton bt_post;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);
        bindViews();
        init(savedInstanceState);
    }

    public void bindViews(){
        toolbar = findViewById(R.id.toolbar_post_listing);
        bt_post = findViewById(R.id.bt_add_new_post);
    }

    public void init(Bundle savedInstanceState){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PostActivity.class));
            }
        });

        if (findViewById(R.id.posts_listing_container)!=null){
            if (savedInstanceState!= null){
                return ;
            }
            PostListingFragment postListingFragment = new PostListingFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.posts_listing_container, postListingFragment,null);
            fragmentTransaction.commit();
        }
    }


}
