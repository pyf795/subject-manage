package com.subjectmanage.mapper;

import com.subjectmanage.beans.group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GroupMapper {

    public List<group> getGroupListByTopicId(int topic_id);
}
