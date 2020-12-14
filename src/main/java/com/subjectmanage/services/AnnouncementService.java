package com.subjectmanage.services;

import com.subjectmanage.beans.Announcement;

import java.util.List;

public interface AnnouncementService {
    public List<Announcement> selectAll(int startIndex, int pageSize);

    public int getTotal();

    public Announcement selectAnnouncementById(int a_id);


    public int addAnnouncement(Announcement announcement);

    public int updateAnnouncement(Announcement announcement);

    public int deleteAnnouncement(int a_id);

    public int batchdeleteAnnouncement(int[] a_ids);

    public List<Announcement> searchAnnouncement(int startIndex, int pageSize,String a_title,String release_time);

    public int searchAnnouncementTotal(String a_title,String release_time);
}
