package ua.pp.lazin.slownews.dao;

import ua.pp.lazin.slownews.entity.User;

import java.util.List;

public interface UserDao {

    void saveOrUpdate(User user);

    List<User> getAll();

    boolean isLoginUnique(String login);

    boolean isEmailUnique(String email);

    User findUserByLogin(String login);
}
