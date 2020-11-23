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
    public List<Group> getGroupListByTopicId(int topic_id) {
        return groupMapper.getGroupListByTopicId(topic_id);
    }

    @Override
    public List<Group> selectAll(int startIndex, int pageSize) {
        return groupMapper.selectAll(startIndex,pageSize);
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
