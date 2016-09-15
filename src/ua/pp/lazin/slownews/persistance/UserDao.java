package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);

    boolean isLoginUnique(String login);

    boolean isEmailUnique(String email);

    User findUserByLogin(String login);
}
