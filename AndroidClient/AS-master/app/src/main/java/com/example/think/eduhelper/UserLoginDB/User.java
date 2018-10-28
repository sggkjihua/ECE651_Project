package com.example.think.eduhelper.UserLoginDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {

    //必须要加一个主键，改名字同样在括号中进行
    private String password;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_email")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
