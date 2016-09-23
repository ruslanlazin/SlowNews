//package ua.pp.lazin.slownews.persistance;
//
//import ua.pp.lazin.slownews.entity.NewsItem;
//import ua.pp.lazin.slownews.entity.User;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NewsDaoJdbc {
//
//    private DataSource dataSource;
//
//    private static NewsDaoJdbc newsDao; //ch
//
//    private NewsDaoJdbc() {
//    }
//
//    public static synchronized NewsDaoJdbc getInstance() { //ch
//        if (newsDao == null) {
//
//            NewsDaoJdbc newsDaoJdbc = new NewsDaoJdbc();
//            try {
//                Context initContext = new InitialContext();
//                newsDaoJdbc.dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/slownews");
//            } catch (NamingException e) {
//                e.printStackTrace();
//            }
//            return newsDao = newsDaoJdbc;
//
//        } else return newsDao;
//    }
//
//    @Override
//    public void saveNewesItem(NewsItem newsItem) {
//
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement(
//                    "INSERT INTO news ( uri, source, title, description, link, pubdate, pathtoimage, favorite)" +
//                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
//            preparedStatement.setString(1, newsItem.getUri());
//            preparedStatement.setString(2, newsItem.getSource());
//            preparedStatement.setString(3, newsItem.getTitle());
//            preparedStatement.setString(4, newsItem.getDescription());
//            preparedStatement.setString(5, newsItem.getLink());
//            preparedStatement.setTimestamp(6, new java.sql.Timestamp(newsItem.getPubDate().getTime()));
//            preparedStatement.setString(7, newsItem.getPathToImage());
//            preparedStatement.setBoolean(8, newsItem.isFavorite());
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                System.out.println("News has been added. Id = " + rs.getInt("id"));
//            }
//            rs.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) try {
//                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//    }
//
//    @Override
//    public boolean isNewsUnique(String uri) {
//
//        boolean isLoginUnique = true;
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM news WHERE uri = ?");
//            preparedStatement.setString(1, uri);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                isLoginUnique = false;
//            }
//            rs.close();
//            preparedStatement.close();
//            return isLoginUnique;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) try {
//                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isEmailUnique(String email) {
//
//        boolean isLoginUnique = true;
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM users WHERE email = ?");
//            preparedStatement.setString(1, email);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                isLoginUnique = false;
//            }
//            rs.close();
//            preparedStatement.close();
//            return isLoginUnique;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) try {
//                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public User findUserByLogin(String login) {
//
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE userlogin = ?");
//            preparedStatement.setString(1, login);
//            ResultSet rs = preparedStatement.executeQuery();
//            User user = null;
//            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getLong("id"));
//                user.setLogin(rs.getString("userlogin"));
//                user.setPassword(rs.getString("userpassword"));
//                user.setEmail(rs.getString("email"));
//                user.setFirstName(rs.getString("firstname"));
//                user.setLastName(rs.getString("lastname"));
//            }
//            rs.close();
//            preparedStatement.close();
//            return user;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) try {
//                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users");
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getLong("id"));
//                user.setLogin(rs.getString("userlogin"));
//                user.setPassword(rs.getString("userpassword"));
//                user.setEmail(rs.getString("email"));
//                user.setFirstName(rs.getString("firstname"));
//                user.setLastName(rs.getString("lastname"));
//                users.add(user);
//            }
//            rs.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) try {
//                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return users;
//    }
//}
