package com.subjectmanage.mapper;


import com.subjectmanage.beans.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

    public List<Score> getScoreByStudent(int student_id);

    public List<Score> getScoreByGroup(int group_id);

    public Score getScore(int group_id,int student_id);

    public Score selectAll(int startIndex,int pageSize);

    public int getTotal(int student_id);

    public int updateScore(Score score);

    public int addScore(Score score);

    public int deleteScore(int topic_id,int student_id);

}
