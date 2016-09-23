package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.entity.NewsItem;

import java.util.List;

/**
 * Created by Laz on 23.09.2016.
 */
public interface NewsDao {

    void saveOrUpdate(NewsItem newsItem);

    List<NewsItem> getAll();

    NewsItem findNewsByUri(String uri);

    void remove(NewsItem newsItem);
}
