package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class NewsDaoHibernate implements NewsDao {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
     private EntityManager em = factory.createEntityManager();

    @Override
    public List<NewsItem> getAllNews() {
        return null;
    }

    @Override
    public void saveNews(NewsItem newsItem) {

        em.getTransaction().begin();
        em.persist(newsItem);
        em.getTransaction().commit();

    }
    @Override
    public void updateNews(NewsItem newsItem) {

        em.getTransaction().begin();
        em.refresh(newsItem);
        em.getTransaction().commit();

    }

    @Override
    public NewsItem findNewsByUri(String uri) {

        em.getTransaction().begin();
        NewsItem newsItem = em.find(NewsItem.class, 1L);
        em.getTransaction().commit();

        return newsItem;
    }

    @Override
    public void remove(NewsItem newsItem) {
        em.getTransaction().begin();
        em.remove(em.contains(newsItem) ? newsItem : em.merge(newsItem));
        em.getTransaction().commit();
    }
}
