package ua.pp.lazin.slownews.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laz on 29.08.2016.
 */
public class UserStorage {
    private List<User> users = new ArrayList<User>();

    public List<User> getAllUsers() {
        return users;
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
}