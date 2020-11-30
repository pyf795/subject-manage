package com.subjectmanage.services;

import com.subjectmanage.beans.Score;
import com.subjectmanage.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> getScoreByStudent(int student_id) {
        return scoreMapper.getScoreByStudent(student_id);
    }

    @Override
    public Score selectAll(int startIndex, int pageSize) {
        return scoreMapper.selectAll(startIndex,pageSize);
    }

    @Override
    public int getTotal(int student_id) {
        return scoreMapper.getTotal(student_id);
    }

    @Override
    public int updateScore(Score score) {
        return scoreMapper.updateScore(score);
    }

    @Override
    public int addScore(Score score) {
        return scoreMapper.addScore(score);
    }

    @Override
    public int deleteScore(int topic_id, int student_id) {
        return scoreMapper.deleteScore(topic_id,student_id);
    }
}
