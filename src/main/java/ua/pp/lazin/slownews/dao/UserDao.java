package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void saveOrUpdate(User user);

    void remove(User user);

    boolean isLoginUnique(String login);

    boolean isEmailUnique(String email);

    User findUserByLogin(String login);
}
