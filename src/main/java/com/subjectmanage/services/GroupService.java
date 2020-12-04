package com.subjectmanage.services;

import com.subjectmanage.beans.Group;

import java.util.List;

public interface GroupService {

    public Group getGroupById(int group_id);

    public List<Group> getGroupWithTopic(int group_id);

    public List<Group> getUncheckGroup(int group_id,int status);

    public List<Group> getGroupListByTopicId(int topic_id);

    public List<Group> selectAll(int startIndex,int pageSize);

    public List<Group> selectTeaGroup(int status,int teacher_id,int startIndex,int pageSize);

    public List<Group> selectTeaGroup1(int status,int teacher_id,int startIndex,int pageSize);

    public int selectTeaGroupTotal(int status,int teacher_id,int startIndex,int pageSize);

    public int selectTeaGroup1Total(int status,int teacher_id,int startIndex,int pageSize);

    public int addGroup(Group group);

    public int updateGroup(Group group);

    public int deleteGroup(int group_id);
}
