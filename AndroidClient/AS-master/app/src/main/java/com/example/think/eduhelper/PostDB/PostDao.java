package com.example.think.eduhelper.PostDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    public void addPost(PostInfo postInfo);

    @Delete
    public void deletePost(PostInfo postInfo);

    @Update
    public void updatePost(PostInfo postInfo);

    @Query("select * from post_record")
    public List<PostInfo> getPost();
}
