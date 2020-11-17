package com.subjectmanage.beans;

public class Topic_type {
    private int topic_type_id;
    private String topic_type_name;

    public int getTopic_type_id() {
        return topic_type_id;
    }

    public void setTopic_type_id(int topic_type_id) {
        this.topic_type_id = topic_type_id;
    }

    public String getTopic_type_name() {
        return topic_type_name;
    }

    public void setTopic_type_name(String topic_type_name) {
        this.topic_type_name = topic_type_name;
    }

    @Override
    public String toString() {
        return "Topic_type{" +
                "topic_type_id=" + topic_type_id +
                ", topic_type_name='" + topic_type_name + '\'' +
                '}';
    }
}
