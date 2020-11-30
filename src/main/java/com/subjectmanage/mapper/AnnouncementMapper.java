package com.subjectmanage.mapper;


import com.subjectmanage.beans.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    public List<Announcement> selectAll(int startIndex,int pageSize);

    public int getTotal();
}
