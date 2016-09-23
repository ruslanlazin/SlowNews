package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoList implements UserDao {

    private List<User> users = new ArrayList<>();
    private static UserDao userDao;

    private UserDaoList() {
    }

    public static synchronized UserDao getInstance() {
        if (userDao == null) {
            return userDao = new UserDaoList();
        } else return userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public boolean isLoginUnique(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmailUnique(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

}