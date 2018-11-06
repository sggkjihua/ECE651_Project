package com.example.think.eduhelper.Post.model;

public class Post {
    private String course;

    public String getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUid() {
        return uid;
    }


    public boolean isStatus() {
        return status;
    }

    private String title;
    private String content;
    private String uid;

    public long getTimestamp() {
        return timestamp;
    }

    public long timestamp;
    private boolean status = false;

    public Post(){}

    public Post(String course, String title, String content, String uid, long timestamp) {
        this.course = course;
        this.title = title;
        this.content = content;
        this.uid = uid;
        this.timestamp = timestamp;
    }


}
