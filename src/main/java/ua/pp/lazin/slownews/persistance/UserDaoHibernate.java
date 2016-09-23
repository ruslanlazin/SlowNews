package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernate implements UserDao {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private static EntityManager em = factory.createEntityManager();

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void saveUser(User user) {

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    @Override
    public void updateUser(User user) {

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

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
        Query query = em.createQuery("select u FROM User u WHERE u.login =:user_login", User.class);
        query.setParameter("user_login", login);
        List<User> users = (List<User>) query.getResultList();
        em.getTransaction().commit();
        if (users.size()==0){
            return null;
        }
        return users.get(0);
    }

    public static void closeEmAndFactory() {
        em.close();
        factory.close();
    }
}
