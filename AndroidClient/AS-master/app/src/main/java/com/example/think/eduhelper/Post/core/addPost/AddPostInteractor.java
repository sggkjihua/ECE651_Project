package com.example.think.eduhelper.Post.core.addPost;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.think.eduhelper.Chat.utils.Constants;
import com.example.think.eduhelper.Post.model.Post;
import com.example.think.eduhelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPostInteractor implements AddPostContractor.Interactor {
    private AddPostContractor.OnPostDatabaseListener mOnPostDatabaseListener;

    public AddPostInteractor(AddPostContractor.OnPostDatabaseListener mOnPostDatabaseListener) {
        this.mOnPostDatabaseListener = mOnPostDatabaseListener;
    }

    @Override
    public void addPostToDatabase(final Context context, Post post) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child(Constants.ARG_POSTS)
                .child(post.getUid()+"_"+String.valueOf(post.getTimestamp()))
                .setValue(post)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mOnPostDatabaseListener.onSuccess(context.getString(R.string.post_successfully_added));
                        } else {
                            mOnPostDatabaseListener.onFailure(context.getString(R.string.post_unable_to_add));
                        }
                    }
                });
    }
}
