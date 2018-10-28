package com.example.think.eduhelper.Mission;

public class Post {
    private int id;
    private String course;
    private String subTitle;
    private String postDetail;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public Post(int id, String course, String subTitle, String postDetail) {
        this.id = id;
        this.course = course;
        this.subTitle = subTitle;
        this.postDetail = postDetail;
    }

}
