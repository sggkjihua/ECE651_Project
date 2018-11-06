package com.example.think.eduhelper;

import android.content.Intent;
import android.os.PersistableBundle;
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
import com.example.think.eduhelper.PostDB.PostInfo;
import com.example.think.eduhelper.UserLoginDB.User;

import java.util.ArrayList;
import java.util.List;

public class TaskPageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    private Button bt_post;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ((TaskAdaptor)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);


        // toolbar and back to home arrow
        toolbar = findViewById(R.id.default_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // recyclerview
        recyclerView = findViewById(R.id.taskRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskAdaptor adaptor = new TaskAdaptor(this, init());
        adaptor.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onParentExpanded(int parentPosition) {

            }

            @Override
            public void onParentCollapsed(int parentPosition) {

            }
        });

        recyclerView.setAdapter(adaptor);
        bt_post = findViewById(R.id.bt_post);
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PostActivity.class));
            }
        });
    }
    public List<TitleParent> init(){
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
}
