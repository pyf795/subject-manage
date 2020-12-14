package com.subjectmanage.mapper;


import com.subjectmanage.beans.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

    public News selectNewsById(int news_id);

    public List<News> selectAll(int startIndex, int pageSize);

    public int getTotal();

    public int addNews(News news);

    public int updateNews(News news);

    public int deleteNews(int news_id);

    public int batchdeleteNews(int[] news_ids);

    public List<News> searchNews(int startIndex, int pageSize,String news_title,String news_date);

    public int searchNewsTotal(String news_title,String news_date);
}
