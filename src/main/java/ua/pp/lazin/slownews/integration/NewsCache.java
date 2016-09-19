package ua.pp.lazin.slownews.integration;

import ua.pp.lazin.slownews.entity.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsCache {
    private static NewsCache instance;
    private List<NewsItem> newsList;

    private NewsCache() {
    }

    public static synchronized NewsCache getInstance() {
        if (instance == null) {
            instance = new NewsCache();
        }
        return instance;
    }

    public synchronized List<NewsItem> getNewsList() {
        return newsList;
    }

    synchronized void setNewsList(List<NewsItem> newsList) {
        this.newsList = newsList;
    }
}
