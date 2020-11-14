package com.subjectmanage.beans;

import java.util.List;

public class group {
    private int group_id;
    private int topic_id;
    private int current_numbers;
    private int volume;
    private List<Student> studentList;


    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getCurrent_numbers() {
        return current_numbers;
    }

    public void setCurrent_numbers(int current_numbers) {
        this.current_numbers = current_numbers;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "group{" +
                "group_id=" + group_id +
                ", topic_id=" + topic_id +
                ", current_numbers=" + current_numbers +
                ", volume=" + volume +
                ", studentList=" + studentList +
                '}';
    }
}
