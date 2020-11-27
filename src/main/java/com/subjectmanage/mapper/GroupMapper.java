package com.subjectmanage.mapper;

import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface GroupMapper {

    public Group getGroupById(int group_id);

    public List<Group> getGroupWithTopic(int group_id);

    public List<Group> getGroupListByTopicId(int topic_id);

    public List<Group> selectAll(int startIndex,int pageSize);

    public int addGroup(Group group);

    public int updateGroup(Group group);

    public int deleteGroup(int group_id);
}
