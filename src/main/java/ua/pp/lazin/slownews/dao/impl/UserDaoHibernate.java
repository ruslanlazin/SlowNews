package ua.pp.lazin.slownews.dao.impl;

import org.hibernate.Criteria;
import ua.pp.lazin.slownews.dao.GenericDao;
import ua.pp.lazin.slownews.dao.UserDao;
import ua.pp.lazin.slownews.entity.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernate extends GenericDao<User> implements UserDao {

//    @Override
//    public List<User> getAll() {
//        em.getTransaction().begin();
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
//        List<User> resultList = query.getResultList();
//        em.getTransaction().commit();
//        return resultList;
//    }

    @Override
    public List<User> getAll() {
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.select(user);
        TypedQuery<User> q = em.createQuery(cq);
        List<User> resultList = q.getResultList();
        em.getTransaction().commit();
    return resultList;
    }

    @Override
    public boolean isLoginUnique(String login) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login =:user_login", User.class);
        query.setParameter("user_login", login);
        List<Object> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmailUnique(String email) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email =:user_email", User.class);
        query.setParameter("user_email", email);
        List<Object> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {

        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery("select u FROM User u WHERE u.login =:user_login", User.class);
        query.setParameter("user_login", login);
        List<User> users = query.getResultList();
        em.getTransaction().commit();
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }
}
