package ua.pp.lazin.slownews.persistance;

import ua.pp.lazin.slownews.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {

    private List<User> users = new ArrayList<User>();

    public List<User> getAllUsers() {
        return users;
    }

    private static UserStorage userStorage;

    private UserStorage() {
    }

    public static synchronized UserStorage getInstance(){
        if (userStorage==null){
            return userStorage = new UserStorage();
        } else return userStorage;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public boolean isLoginUnique(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmailUnique(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public User findUserbyLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}