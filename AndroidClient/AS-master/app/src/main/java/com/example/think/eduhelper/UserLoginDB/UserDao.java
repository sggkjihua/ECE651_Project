package com.example.think.eduhelper.UserLoginDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.think.eduhelper.UserLoginDB.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User user);

    @Delete
    public void deleteUser(User user);

    @Update
    public void updateUser(User user);

    @Query("Select * from users")
    public List<User> getUsers();
}
