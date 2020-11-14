package com.subjectmanage.services;

import com.subjectmanage.beans.group;

import java.util.List;

public interface GroupService {
    public List<group> getGroupListByTopicId(int topic_id);
}
