package ua.pp.lazin.slownews.dao.impl;

import ua.pp.lazin.slownews.dao.NewsDao;
import ua.pp.lazin.slownews.entity.NewsItem;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoJdbc implements NewsDao {

    private DataSource dataSource;

    private static NewsDaoJdbc newsDao;

    private NewsDaoJdbc() {
    }

    public static synchronized NewsDao getInstance() {
        if (newsDao == null) {

            NewsDaoJdbc newsDaoJdbc = new NewsDaoJdbc();
            try {
                Context initContext = new InitialContext();
                newsDaoJdbc.dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/slownews");
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return newsDao = newsDaoJdbc;

        } else return newsDao;
    }

    public void saveNewesItem(NewsItem newsItem) {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO news ( uri, source, title, description, link, pubdate, pathtoimage, favorite)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
            preparedStatement.setString(1, newsItem.getUri());
            preparedStatement.setString(2, newsItem.getSource());
            preparedStatement.setString(3, newsItem.getTitle());
            preparedStatement.setString(4, newsItem.getDescription());
            preparedStatement.setString(5, newsItem.getLink());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(newsItem.getPubDate().getTime()));
            preparedStatement.setString(7, newsItem.getPathToImage());
            preparedStatement.setBoolean(8, newsItem.isFavorite());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("News has been added. Id = " + rs.getInt("id"));
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

    @Override
    public List<NewsItem> getAll() {
        Connection con = null;
        List<NewsItem> resultList = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM news");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setId(rs.getLong("id"));
                newsItem.setUri(rs.getString("uri"));
                newsItem.setSource(rs.getString("source"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setDescription(rs.getString("description"));
                newsItem.setLink(rs.getString("link"));
                newsItem.setPubDate(rs.getTimestamp("pubDate"));
                newsItem.setPathToImage(rs.getString("pathToImage"));
                newsItem.setFavorite(rs.getBoolean("favorite"));
                resultList.add(newsItem);
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
        return resultList;
    }

    @Override
    public void remove(NewsItem newsItem) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("DELETE * FROM news WHERE news.id=?");
            preparedStatement.setLong(1, newsItem.getId());
            preparedStatement.executeQuery();
            con.commit();
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

    @Override
    public NewsItem findNewsByUri(String uri) {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM news WHERE uri = ?");
            preparedStatement.setString(1, uri);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setId(rs.getLong("id"));
                newsItem.setUri(rs.getString("uri"));
                newsItem.setSource(rs.getString("source"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setDescription(rs.getString("descrittion"));
                newsItem.setLink(rs.getString("link"));
                newsItem.setPubDate(rs.getTimestamp("pubDate"));
                newsItem.setPathToImage(rs.getString("pathToImage"));
                newsItem.setFavorite(rs.getBoolean("favorite"));

                rs.close();
                preparedStatement.close();
                return newsItem;
            }
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
    public NewsItem addIfUnique(NewsItem newsItem) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM news WHERE uri = ?");
            preparedStatement.setString(1, newsItem.getUri());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                newsItem.setId(rs.getLong("id"));
                rs.close();
                preparedStatement.close();
            } else {
                rs.close();
                preparedStatement.close();
                PreparedStatement preparedStatementInsert = con.prepareStatement(
                        "INSERT INTO news ( uri, source, title, description, link, pubdate, pathtoimage, favorite)" +
                                " VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
                preparedStatementInsert.setString(1, newsItem.getUri());
                preparedStatementInsert.setString(2, newsItem.getSource());
                preparedStatementInsert.setString(3, newsItem.getTitle());
                preparedStatementInsert.setString(4, newsItem.getDescription());
                preparedStatementInsert.setString(5, newsItem.getLink());
                preparedStatementInsert.setTimestamp(6, new java.sql.Timestamp(newsItem.getPubDate().getTime()));
                preparedStatementInsert.setString(7, newsItem.getPathToImage());
                preparedStatementInsert.setBoolean(8, newsItem.isFavorite());
                ResultSet rsi = preparedStatementInsert.executeQuery();
                if (rsi.next()) {
                    newsItem.setId(rsi.getLong("id"));
                }
                rsi.close();
                preparedStatementInsert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return newsItem;
    }


    @Override
    public void removeIfUnused(NewsItem newsItem) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM news WHERE " +
                    "(id = ? AND (SELECT COUNT(*) FROM users_news WHERE personalnews_id = ?) = 0)");
            preparedStatement.setLong(1, newsItem.getId());
            preparedStatement.setLong(2, newsItem.getId());
            int deleted = preparedStatement.executeUpdate();
            System.out.println(deleted + " item was deleted");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
