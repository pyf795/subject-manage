package com.subjectmanage.services;

import com.subjectmanage.beans.Group;
import com.subjectmanage.mapper.GroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public Group getGroupById(int group_id) {
        return groupMapper.getGroupById(group_id);
    }

    @Override
    public List<Group> getGroupWithTopic(int group_id) {
        return groupMapper.getGroupWithTopic(group_id);
    }

    @Override
    public List<Group> getUncheckGroup(int group_id, int status) {
        return groupMapper.getUncheckGroup(group_id, status);
    }

    @Override
    public List<Group> getGroupListByTopicId(int topic_id) {
        return groupMapper.getGroupListByTopicId(topic_id);
    }

    @Override
    public List<Group> selectAll(int startIndex, int pageSize) {
        return groupMapper.selectAll(startIndex,pageSize);
    }

      @Override
    public List<Group> selectTeaGroup(int status, int teacher_id, int startIndex, int pageSize) {
        return groupMapper.selectTeaGroup(status, teacher_id, startIndex, pageSize);
    }

    @Override
    public List<Group> selectTeaGroup1(int status, int teacher_id, int startIndex, int pageSize) {
        return groupMapper.selectTeaGroup1(status, teacher_id, startIndex, pageSize);
    }

    @Override
    public int selectTeaGroupTotal(int status, int teacher_id, int startIndex, int pageSize) {
        return groupMapper.selectTeaGroupTotal(status, teacher_id, startIndex, pageSize);
    }

    @Override
    public int selectTeaGroup1Total(int status, int teacher_id, int startIndex, int pageSize) {
        return groupMapper.selectTeaGroupTotal(status, teacher_id, startIndex, pageSize);
    }

    @Override
    public int addGroup(Group group) {
        return groupMapper.addGroup(group);
    }

    @Override
    public int updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    @Override
    public int deleteGroup(int group_id) {
        return groupMapper.deleteGroup(group_id);
    }
}
