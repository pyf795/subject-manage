package com.subjectmanage.mapper;


import com.subjectmanage.beans.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    public List<News> selectAll(int startIndex, int pageSize);

    public int getTotal();
}
