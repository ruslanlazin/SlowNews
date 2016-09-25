package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.GenericDao;
import ua.pp.lazin.slownews.dao.NewsDao;
import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NewsDaoHibernate extends GenericDao<NewsItem> implements NewsDao {

    public NewsDaoHibernate() {
        super(NewsItem.class);
    }

    @Override
    public NewsItem findNewsByUri(String uri) {

        em.getTransaction().begin();
        TypedQuery<NewsItem> query = em.createQuery("select n FROM NewsItem n WHERE n.uri =:news_uri", NewsItem.class);
        query.setParameter("news_uri", uri);
        List<NewsItem> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public NewsItem addIfUnique(NewsItem newsItem) {

        em.getTransaction().begin();
        TypedQuery<NewsItem> query = em.createQuery("select n FROM NewsItem n WHERE n.uri =:news_uri", NewsItem.class);
        query.setParameter("news_uri", newsItem.getUri());
        List<NewsItem> resultList = query.getResultList();
        if (resultList.size() == 0) {
            newsItem.clearId();
            em.persist(newsItem);
        } else {
            newsItem = resultList.get(0);
        }
        em.getTransaction().commit();
        return newsItem;
    }

    @Override
    public void removeIfUnused(NewsItem newsItem) {

        em.getTransaction().begin();
        final String SQL = "SELECT DISTINCT personalNews_id FROM users_news WHERE users_news.personalNews_id = ?";
        Query query = em.createNativeQuery(SQL);
        query.setParameter(1, newsItem.getId());
        List<Object> resultList = query.getResultList();
        em.flush();
        if (resultList.size() == 0) {
            em.remove(newsItem);
        }
        em.getTransaction().commit();
    }
}
