package com.subjectmanage.beans;

public class topic_comment {
    private int topic_id;
    private int student_id;
    private String content;
    private String comment_time;


    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    @Override
    public String toString() {
        return "topic_comment{" +
                "topic_id=" + topic_id +
                ", student_id=" + student_id +
                ", content='" + content + '\'' +
                ", comment_time='" + comment_time + '\'' +
                '}';
    }
}
