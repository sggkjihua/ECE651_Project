package com.example.think.eduhelper.Post.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.Post.core.addPost.AddPostContractor;
import com.example.think.eduhelper.Post.core.addPost.AddPostPresenter;
import com.example.think.eduhelper.Post.model.Post;
import com.example.think.eduhelper.R;
import com.example.think.eduhelper.TaskPageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements View.OnClickListener, AddPostContractor.View {
    private EditText course, title, content, topic;
    private AddPostPresenter mAddPostPresenter;
    private ProgressDialog mProgressDialog;
    private FirebaseUser firebaseUser;
    private Button bt_post_confirm;


    public PostFragment() {
        // Required empty public constructor
    }

    public void bindViews(View view){
        course = view.findViewById(R.id.post_course);
        title = view.findViewById(R.id.post_subtitle);
        content = view.findViewById(R.id.post_detail);
        topic = view.findViewById(R.id.post_topic);
        bt_post_confirm = view.findViewById(R.id.post_confirm);
    }

    public void init(){
        mAddPostPresenter = new AddPostPresenter(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        bt_post_confirm.setOnClickListener(this);
    }

    public void addPost(View view){
        String post_course = course.getText().toString();
        String post_title = title.getText().toString();
        String post_detail = content.getText().toString();
        String post_topic = topic.getText().toString();
        Post post = new Post(post_course, post_title,post_detail, firebaseUser.getUid(),System.currentTimeMillis(),post_topic);
        if (post_course.isEmpty() || post_title.isEmpty() || post_detail.isEmpty()) {
            Toast.makeText(getActivity(), "Please complete information", Toast.LENGTH_SHORT).show();
        } else {
            mProgressDialog.show();
            mAddPostPresenter.addPost(getActivity(),post);
            //mRegisterPresenter.register(SignUpActivity.this, userEmail, userPassword);
        }
        Toast.makeText(getActivity(), "Please complete information", Toast.LENGTH_SHORT).show();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        bindViews(view);
        init();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.post_confirm:
                addPost(v);
        }
    }

    @Override
    public void onAddPostSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), TaskPageActivity.class));
    }

    @Override
    public void onAddPostFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
