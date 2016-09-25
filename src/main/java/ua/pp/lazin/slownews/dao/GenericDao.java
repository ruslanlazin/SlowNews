package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class GenericDao<T> {

    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    protected static EntityManager em = factory.createEntityManager();
    protected final Class<T> entityClass;

    protected GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> getAll() {
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> result = cq.from(entityClass);
        cq.select(result);
        TypedQuery<T> q = em.createQuery(cq);
        List<T> resultList = q.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public void saveOrUpdate(T entity) {
        em.getTransaction().begin();
        if (em.contains(entity)) {
            em.merge(entity);
        } else em.persist(entity);
        em.getTransaction().commit();
    }

    public void remove(T entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    public static void closeEmAndFactory() {
        em.close();
        factory.close();
    }


}


