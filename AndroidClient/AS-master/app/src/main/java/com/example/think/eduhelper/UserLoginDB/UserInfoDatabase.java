package com.example.think.eduhelper.UserLoginDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserInfoDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
