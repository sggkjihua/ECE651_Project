package com.example.think.eduhelper.Post.core.addPost;

import android.content.Context;
import com.example.think.eduhelper.Post.model.Post;

public interface AddPostContractor {
    interface View {
        void onAddPostSuccess(String message);

        void onAddPostFailure(String message);
    }

    interface Presenter {
        void addPost(Context context, Post post);
    }

    interface Interactor {
        void addPostToDatabase(Context context, Post post);
    }

    interface OnPostDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
