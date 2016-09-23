package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.GenericDao;
import ua.pp.lazin.slownews.dao.NewsDao;
import ua.pp.lazin.slownews.entity.NewsItem;

import java.util.List;

public class NewsDaoHibernate extends GenericDao<NewsItem> implements NewsDao {

    @Override
    public List<NewsItem> getAll() {
        return null;
    }

    @Override
    public NewsItem findNewsByUri(String uri) {

        em.getTransaction().begin();
        NewsItem newsItem = em.find(NewsItem.class, 1L);
        em.getTransaction().commit();

        return newsItem;
    }
}
