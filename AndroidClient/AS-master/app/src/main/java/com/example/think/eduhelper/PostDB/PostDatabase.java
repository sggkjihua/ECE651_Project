package com.example.think.eduhelper.PostDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {PostInfo.class},version = 1,exportSchema = false)
public abstract class PostDatabase extends RoomDatabase{
    public abstract PostDao postDao();
}
