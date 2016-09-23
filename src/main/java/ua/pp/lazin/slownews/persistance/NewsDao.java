package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.NewsItem;

import java.util.List;

/**
 * Created by Laz on 23.09.2016.
 */
public interface NewsDao {
    List<NewsItem> getAllNews();

    void saveNews(NewsItem newsItem);

    void updateNews(NewsItem newsItem);

    NewsItem findNewsByUri(String uri);

    void remove(NewsItem newsItem);
}
