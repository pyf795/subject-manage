package com.subjectmanage.services;

import com.subjectmanage.beans.Group;

import java.util.List;

public interface GroupService {

    public Group getGroupById(int group_id);

    public List<Group> getGroupListByTopicId(int topic_id);

    public List<Group> selectAll(int startIndex,int pageSize);

    public int addGroup(Group group);

    public int updateGroup(Group group);

    public int deleteGroup(int group_id);
}
