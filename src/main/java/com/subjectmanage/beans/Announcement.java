package com.subjectmanage.beans;

public class Announcement {
    private int a_id;
    private String a_title;
    private String a_content;
    private String release_time;

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_title() {
        return a_title;
    }

    public void setA_title(String a_title) {
        this.a_title = a_title;
    }

    public String getA_content() {
        return a_content;
    }

    public void setA_content(String a_content) {
        this.a_content = a_content;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "a_id=" + a_id +
                ", a_title='" + a_title + '\'' +
                ", a_content='" + a_content + '\'' +
                ", release_time='" + release_time + '\'' +
                '}';
    }
}
