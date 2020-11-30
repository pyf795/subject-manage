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
    public List<News> selectAll(int startIndex, int pageSize) {
        return newsMapper.selectAll(startIndex, pageSize);
    }

    @Override
    public int getTotal() {
        return newsMapper.getTotal();
    }
}
