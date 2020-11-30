package com.subjectmanage.beans;

public class Score {
    private int student_id;
    private int topic_id;
    private int group_id;
    private int score;
    private Student student;
    private Topic topic;
    private Group group;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "student_id=" + student_id +
                ", topic_id=" + topic_id +
                ", group_id=" + group_id +
                ", score=" + score +
                ", student=" + student +
                ", topic=" + topic +
                ", group=" + group +
                '}';
    }
}
