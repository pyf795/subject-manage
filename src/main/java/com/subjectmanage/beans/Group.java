package com.subjectmanage.beans;

import java.util.List;

public class Group {
    private int group_id;
    private int topic_id;
    private int current_numbers;
    private int volume;
    private List<Student> studentList;
    private Topic topic;
    private int mfile_id;
    private int efile_id;
    private File mfile;
    private File efile;

    public int getMfile_id() {
        return mfile_id;
    }

    public void setMfile_id(int mfile_id) {
        this.mfile_id = mfile_id;
    }

    public int getEfile_id() {
        return efile_id;
    }

    public void setEfile_id(int efile_id) {
        this.efile_id = efile_id;
    }

    public File getMfile() {
        return mfile;
    }

    public void setMfile(File mfile) {
        this.mfile = mfile;
    }

    public File getEfile() {
        return efile;
    }

    public void setEfile(File efile) {
        this.efile = efile;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

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
        return "Group{" +
                "group_id=" + group_id +
                ", topic_id=" + topic_id +
                ", current_numbers=" + current_numbers +
                ", volume=" + volume +
                ", studentList=" + studentList +
                ", topic=" + topic +
                ", mfile_id=" + mfile_id +
                ", efile_id=" + efile_id +
                ", mfile=" + mfile +
                ", efile=" + efile +
                '}';
    }
}
