package com.subjectmanage.beans;

import java.util.List;

public class Topic {
    private int topic_id;
    private int teacher_id;
    private String headline;
    private String release_time;
    private String topic_url;
    private String type;
    private List<Group> groupList;

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

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
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

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", teacher_id=" + teacher_id +
                ", headline='" + headline + '\'' +
                ", release_time='" + release_time + '\'' +
                ", topic_url='" + topic_url + '\'' +
                ", type='" + type + '\'' +
                ", groupList=" + groupList +
                '}';
    }
}
