package com.subjectmanage.beans;

public class file {
    private int file_id;
    private int group_id;
    private String headline;
    private String release_time;
    private String file_url;
    private String type;
    private String topic_id;

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    @Override
    public String toString() {
        return "file{" +
                "file_id=" + file_id +
                ", group_id=" + group_id +
                ", headline='" + headline + '\'' +
                ", release_time='" + release_time + '\'' +
                ", file_url='" + file_url + '\'' +
                ", type='" + type + '\'' +
                ", topic_id='" + topic_id + '\'' +
                '}';
    }
}
