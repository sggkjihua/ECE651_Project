package com.example.think.eduhelper.Post.core.getPost;

import com.example.think.eduhelper.Post.model.Post;

import java.util.List;

public class GetPostPresenter implements GetPostConstractor.Presenter, GetPostConstractor.OnGetAllPostsListener {
    private GetPostConstractor.View mView;
    private GetPostInteractor mGetPostsInteractor;

    public GetPostPresenter(GetPostConstractor.View view) {
        this.mView = view;
        mGetPostsInteractor = new GetPostInteractor(this);
    }

    @Override
    public void getAllPosts() {
        mGetPostsInteractor.getAllPostsFromFirebase();
    }

    @Override
    public void getChatPosts() {
        mGetPostsInteractor.getAllPostsFromFirebase();
    }

    @Override
    public void onGetAllPostsSuccess(List<Post> posts) {
        mView.onGetAllPostsSuccess(posts);
    }

    @Override
    public void onGetAllPostsFailure(String message) {
        mView.onGetAllPostsFailure(message);
    }
}
