package ua.pp.lazin.slownews.controller;

import ua.pp.lazin.slownews.integration.NewsCache;
import ua.pp.lazin.slownews.entity.*;
import ua.pp.lazin.slownews.tests.TestDbConnection;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/news")
public class News extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<NewsItem> newsList = NewsCache.getInstance().getNewsList();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {

            List<NewsItem> tmpNewsList = new ArrayList<>(newsList.size());

            for (NewsItem newsItem : newsList) {
                NewsItem clone = (NewsItem) newsItem.clone();
                if (user.getPersonalNews().contains(clone)) {
                    clone.setFavorite(true);
                }
                tmpNewsList.add(clone);
            }
            newsList = tmpNewsList;
        }
        request.getSession().setAttribute("newsList", newsList);


        System.out.println(newsList.size());
        // TODO delete test db from here
        try {
            TestDbConnection.testDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);

    }
}