package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.GenericDao;
import ua.pp.lazin.slownews.dao.UserDao;
import ua.pp.lazin.slownews.entity.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoHibernate extends GenericDao<User> implements UserDao {

    public UserDaoHibernate(){
        super(User.class);
    }


//    @Override
//    public List<User> getAll() {
//        em.getTransaction().begin();
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
//        List<User> resultList = query.getResultList();
//        em.getTransaction().commit();
//        return resultList;
//    }


    @Override
    public boolean isLoginUnique(String login) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT u.id FROM User u WHERE u.login =:user_login");
        query.setParameter("user_login", login);
        List<Long> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmailUnique(String email) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT u.id FROM User u WHERE u.email =:user_email");
        query.setParameter("user_email", email);
        List<Long> resultList = query.getResultList();
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
