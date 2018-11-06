package com.example.think.eduhelper.Post.core.addPost;

import android.content.Context;

import com.example.think.eduhelper.Post.model.Post;

public class AddPostPresenter implements AddPostContractor.Presenter, AddPostContractor.OnPostDatabaseListener {
    private AddPostContractor.View mView;
    private AddPostInteractor mAddPostInteractor;

    public AddPostPresenter(AddPostContractor.View mView) {
        this.mView = mView;
        mAddPostInteractor = new AddPostInteractor(this);
    }


    @Override
    public void addPost(Context context, Post post) {
        mAddPostInteractor.addPostToDatabase(context, post);
    }

    @Override
    public void onSuccess(String message) {
        mView.onAddPostSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mView.onAddPostFailure(message);
    }
}
