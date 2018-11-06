package com.example.think.eduhelper.Post.core.getPost;

import com.example.think.eduhelper.Post.model.Post;

import java.util.List;

public interface GetPostConstractor {
    interface View {
        void onGetAllPostsSuccess(List<Post> posts);

        void onGetAllPostsFailure(String message);

        void onGetSelectedPostsSuccess(List<Post> posts);

        void onGetSelectedPostssFailure(String message);
    }

    interface Presenter {
        void getAllPosts();

        void getChatPosts();
    }

    interface Interactor {
        void getAllPostsFromFirebase();

        void getSelectedPostsFromFirebase();
    }

    interface OnGetAllPostsListener {
        void onGetAllPostsSuccess(List<Post> posts);

        void onGetAllPostsFailure(String message);
    }

    interface OnGetSelectedPostsListener {
        void onGetSelectedPostsSuccess(List<Post> posts);

        void onGetSelectedPostsFailure(String message);
    }
}
