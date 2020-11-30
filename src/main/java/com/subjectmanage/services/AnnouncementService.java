package com.subjectmanage.services;

import com.subjectmanage.beans.Announcement;

import java.util.List;

public interface AnnouncementService {
    public List<Announcement> selectAll(int startIndex, int pageSize);
    public int getTotal();
}
