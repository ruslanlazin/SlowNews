package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.dao.impl.*;

public class DaoFactory {
    private static final int storageType = 1;  //  1-List  2-JDBC  3-Hibernate

    public static UserDao getUserDao() {

        if (storageType == 1) {
            return UserDaoList.getInstance();
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
            return NewsDaoJdbc.getInstance();
        }
        if (storageType == 3) {
            return new NewsDaoHibernate();
        }
        return null;
    }
}