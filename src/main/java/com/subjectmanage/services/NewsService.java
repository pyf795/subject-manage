package com.subjectmanage.services;

import com.subjectmanage.beans.News;

import java.util.List;

public interface NewsService {

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
