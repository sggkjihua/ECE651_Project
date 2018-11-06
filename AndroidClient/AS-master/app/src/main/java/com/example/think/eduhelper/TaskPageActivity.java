package com.example.think.eduhelper;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.think.eduhelper.Adaptor.TaskAdaptor;
import com.example.think.eduhelper.Models.TitleChild;
import com.example.think.eduhelper.Models.TitleCreator;
import com.example.think.eduhelper.Models.TitleParent;
import com.example.think.eduhelper.Post.ui.PostActivity;
import com.example.think.eduhelper.Post.ui.PostFragment;
import com.example.think.eduhelper.Post.ui.PostListingFragment;
import com.example.think.eduhelper.PostDB.PostInfo;
import com.example.think.eduhelper.UserLoginDB.User;

import java.util.ArrayList;
import java.util.List;

public class TaskPageActivity extends AppCompatActivity {
    Toolbar toolbar;
    private FloatingActionButton bt_post;

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
    public List<TitleParent> getPosts(){
        List<PostInfo> posts= MainActivity.postDatabase.postDao().getPost();
        List<TitleParent> parents = new ArrayList<>();
        for(PostInfo post:posts){
            List<Object>  titleChildren = new ArrayList<>();
            TitleParent parent = new TitleParent(post.getCourse(),post.getTitle());
            TitleChild child = new TitleChild(post.getDetail());
            titleChildren.add(child);
            parent.setChildrenList(titleChildren);
            parents.add(parent);
        }
        return parents;
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
