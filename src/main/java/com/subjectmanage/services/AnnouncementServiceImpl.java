package com.subjectmanage.services;

import com.subjectmanage.beans.Announcement;
import com.subjectmanage.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> selectAll(int startIndex, int pageSize) {
        return announcementMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int getTotal(){
        return announcementMapper.getTotal();
    }
}
