package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.entity.NewsItem;

import java.util.List;

/**
 * Created by Laz on 23.09.2016.
 */
public interface NewsDao {

    List<NewsItem> getAll();

    //void saveOrUpdate(NewsItem newsItem);

    void remove(NewsItem newsItem);

    NewsItem findNewsByUri(String uri);

    NewsItem addIfUnique(NewsItem newsItem);

    void removeIfUnused(NewsItem newsItem);
}
