package com.subjectmanage.services;

import com.subjectmanage.beans.group;
import com.subjectmanage.mapper.GroupMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public List<group> getGroupListByTopicId(int topic_id) {
        return groupMapper.getGroupListByTopicId(topic_id);
    }
}
