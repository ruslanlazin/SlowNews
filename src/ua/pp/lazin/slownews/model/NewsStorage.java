package ua.pp.lazin.slownews.model;

import java.util.ArrayList;
import java.util.List;

public class NewsStorage {
    private static NewsStorage instance;
    private List<NewsItem> newsList = new ArrayList<NewsItem>(50);

    private NewsStorage() {
    }

    public static synchronized NewsStorage getInstance() {
        if (instance == null) {
            instance = new NewsStorage();
        }
        return instance;
    }

    public synchronized List<NewsItem> getNewsList() {
        return newsList;
    }

    public synchronized void setNewsList(List<NewsItem> newsList) {
        this.newsList = newsList;
    }
}
