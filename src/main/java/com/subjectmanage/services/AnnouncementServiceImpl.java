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

    @Override
    public Announcement selectAnnouncementById(int a_id) {
        return announcementMapper.selectAnnouncementById(a_id);
    }

    @Override
    public int addAnnouncement(Announcement announcement) {
        return announcementMapper.addAnnouncement(announcement);
    }

    @Override
    public int updateAnnouncement(Announcement announcement) {
        return announcementMapper.updateAnnouncement(announcement);
    }

    @Override
    public int deleteAnnouncement(int a_id) {
        return announcementMapper.deleteAnnouncement(a_id);
    }

    @Override
    public int batchdeleteAnnouncement(int[] a_ids) {
        return announcementMapper.batchdeleteAnnouncement(a_ids);
    }

    @Override
    public List<Announcement> searchAnnouncement(int startIndex, int pageSize, String a_title, String release_time) {
        return announcementMapper.searchAnnouncement(startIndex, pageSize, a_title, release_time);
    }

    @Override
    public int searchAnnouncementTotal(String a_title, String release_time) {
        return announcementMapper.searchAnnouncementTotal(a_title, release_time);
    }
}
