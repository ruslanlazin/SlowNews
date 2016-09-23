package ua.pp.lazin.slownews.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class GenericDao<T> {

    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    protected static EntityManager em = factory.createEntityManager();

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


