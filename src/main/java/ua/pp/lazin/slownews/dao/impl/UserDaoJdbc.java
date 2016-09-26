package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.UserDao;
import ua.pp.lazin.slownews.entity.NewsItem;
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
                    "INSERT INTO users (login, password, email, firstname, lastname)" +
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
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatementUpdate = con.prepareStatement
                    ("UPDATE users SET login = ?, password = ?, email = ? ,firstName = ?, lastName = ? WHERE id = ?");
            preparedStatementUpdate.setString(1, user.getLogin());
            preparedStatementUpdate.setString(2, user.getPassword());
            preparedStatementUpdate.setString(3, user.getEmail());
            preparedStatementUpdate.setString(4, user.getFirstName());
            preparedStatementUpdate.setString(5, user.getLastName());
            preparedStatementUpdate.setLong(6, user.getId());
            preparedStatementUpdate.executeUpdate();
            preparedStatementUpdate.close();

            con.setAutoCommit(false);
            PreparedStatement preparedStatementDelete = con.prepareStatement("DELETE FROM users_news WHERE user_id = ?");
            preparedStatementDelete.setLong(1, user.getId());
            preparedStatementDelete.executeUpdate();
            preparedStatementDelete.close();

            if (user.getPersonalNews() != null && user.getPersonalNews().size() != 0) {
                PreparedStatement preparedStatementInsert = con.prepareStatement(
                        "INSERT INTO users_news ( user_id, personalnews_id)" +
                                " VALUES (?, ?) ");
                preparedStatementInsert.setLong(1, user.getId());
                for (NewsItem newsItem : user.getPersonalNews()) {
                    preparedStatementInsert.setLong(2, newsItem.getId());
                    preparedStatementInsert.executeUpdate();
                }
                preparedStatementInsert.close();
            }
            con.commit();

        } catch (SQLException e) {
            System.out.println("Transaction is being rolled back");
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.setAutoCommit(true);
                con.close();
            } catch (Exception ignore) {
            }
        }
    }


    @Override
    public boolean isLoginUnique(String login) {


        Connection con = null;
        try {
            con = dataSource.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM users WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            boolean isLoginUnique = true;
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
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                rs.close();
                preparedStatement.close();

                PreparedStatement preparedStatementForNews = con.prepareStatement
                        ("SELECT news.* FROM news JOIN users_news ON news.id=users_news.personalnews_id WHERE users_news.user_id = ?");
                preparedStatementForNews.setLong(1, user.getId());
                ResultSet rsForNews = preparedStatementForNews.executeQuery();
                while (rsForNews.next()) {
                    NewsItem newsItem = new NewsItem();
                    newsItem.setId(rsForNews.getLong("id"));
                    newsItem.setUri(rsForNews.getString("uri"));
                    newsItem.setSource(rsForNews.getString("source"));
                    newsItem.setTitle(rsForNews.getString("title"));
                    newsItem.setDescription(rsForNews.getString("description"));
                    newsItem.setLink(rsForNews.getString("link"));
                    newsItem.setPubDate(rsForNews.getTimestamp("pubDate"));
                    newsItem.setPathToImage(rsForNews.getString("pathToImage"));
                    newsItem.setFavorite(rsForNews.getBoolean("favorite"));
                    user.getPersonalNews().add(newsItem);
                }
                rsForNews.close();
                preparedStatementForNews.close();
            }
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
        if (user.getId() == null) {
            saveUser(user);
        } else updateUser(user);
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
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
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
