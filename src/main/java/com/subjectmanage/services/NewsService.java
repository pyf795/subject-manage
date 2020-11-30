package com.subjectmanage.services;

import com.subjectmanage.beans.News;

import java.util.List;

public interface NewsService {
    public List<News> selectAll(int startIndex, int pageSize);

    public int getTotal();
}
