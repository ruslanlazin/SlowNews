package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.dao.impl.NewsDaoHibernate;
import ua.pp.lazin.slownews.dao.impl.UserDaoHibernate;
import ua.pp.lazin.slownews.dao.impl.UserDaoJdbc;

public class DaoFactory {
    private static final int storageType = 3;  //  1-List  2-JDBC  3-Hibernate

    public static UserDao getUserDao() {

        if (storageType == 1) {
            return UserDaoJdbc.getInstance();
        }
        if (storageType == 2) {
            return UserDaoJdbc.getInstance();
        }
        if (storageType == 3) {
            return new UserDaoHibernate();
        }
        return null;
    }

    public static NewsDao getNewsDao(){
        if (storageType == 1) {
            return null;
        }
        if (storageType == 2) {
            return null;
        }
        if (storageType == 3) {
            return new NewsDaoHibernate();
        }
        return null;
    }
}