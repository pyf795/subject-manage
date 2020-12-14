package com.subjectmanage.services;


import com.subjectmanage.beans.News;
import com.subjectmanage.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public News selectNewsById(int news_id) {
        return newsMapper.selectNewsById(news_id);
    }

    @Override
    public List<News> selectAll(int startIndex, int pageSize) {
        return newsMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int getTotal() {
        return newsMapper.getTotal();
    }

    @Override
    public int addNews(News news) {
        return newsMapper.addNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNews(int news_id) {
        return newsMapper.deleteNews(news_id);
    }

    @Override
    public int batchdeleteNews(int[] news_ids) {
        return newsMapper.batchdeleteNews(news_ids);
    }

    @Override
    public List<News> searchNews(int startIndex, int pageSize, String news_title, String news_date) {
        return newsMapper.searchNews(startIndex, pageSize, news_title, news_date);
    }

    @Override
    public int searchNewsTotal(String news_title, String news_date) {
        return newsMapper.searchNewsTotal(news_title,news_date);
    }
}
