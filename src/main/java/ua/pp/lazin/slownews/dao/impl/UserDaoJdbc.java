package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.UserDao;
import ua.pp.lazin.slownews.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    private static UserDao userDao;

    private UserDaoJdbc() {
    }

    public static synchronized UserDao getInstance() {
        if (userDao == null) {

            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            try {
                Context initContext = new InitialContext();
                userDaoJdbc.dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/slownews");
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return userDao = userDaoJdbc;

        } else return userDao;
    }


    public void saveUser(User user) {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO users (userlogin, userpassword, email, firstname, lastname)" +
                            " VALUES (?, ?, ?, ?, ?) RETURNING id");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }


    public void updateUser(User user) {

    }

    @Override
    public boolean isLoginUnique(String login) {

        boolean isLoginUnique = true;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM users WHERE userlogin = ?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                isLoginUnique = false;
            }
            rs.close();
            preparedStatement.close();
            return isLoginUnique;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    @Override
    public boolean isEmailUnique(String email) {

        boolean isLoginUnique = true;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                isLoginUnique = false;
            }
            rs.close();
            preparedStatement.close();
            return isLoginUnique;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE userlogin = ?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("userlogin"));
                user.setPassword(rs.getString("userpassword"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
            }
            rs.close();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public void saveOrUpdate(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("userlogin"));
                user.setPassword(rs.getString("userpassword"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                users.add(user);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return users;
    }
}
