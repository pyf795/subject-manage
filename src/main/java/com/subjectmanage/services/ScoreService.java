package com.subjectmanage.services;

import com.subjectmanage.beans.Score;

import java.util.List;

public interface ScoreService {

    public List<Score> getScoreByStudent(int student_id);

    public Score selectAll(int startIndex,int pageSize);

    public int getTotal(int student_id);

    public int updateScore(Score score);

    public int addScore(Score score);

    public int deleteScore(int topic_id,int student_id);
}
