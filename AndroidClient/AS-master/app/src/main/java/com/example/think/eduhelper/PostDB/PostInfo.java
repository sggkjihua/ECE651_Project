package com.example.think.eduhelper.PostDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "post_record")
public class PostInfo {
    @PrimaryKey(autoGenerate = true)
    private int id=0;

    private String title;
    @ColumnInfo(name = "CourseName")
    private String course;
    @ColumnInfo(name = "Detail")
    private String detail;
    @ColumnInfo(name = "Post_status")
    private Boolean status = false;



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
