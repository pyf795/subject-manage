package com.subjectmanage.beans;

import java.util.List;

public class Topic {
    private int topic_id;
    private int teacher_id;
    private String teacher_name;
    private String headline;
    private String release_time;
    private int file_id;
    private String type;
    private List<Group> groupList;
    private Teacher teacher;
    private String content;
    private String grade;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", teacher_id=" + teacher_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", headline='" + headline + '\'' +
                ", release_time='" + release_time + '\'' +
                ", file_id=" + file_id +
                ", type='" + type + '\'' +
                ", groupList=" + groupList +
                ", teacher=" + teacher +
                ", content='" + content + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
