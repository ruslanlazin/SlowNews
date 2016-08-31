
package ua.pp.lazin.slownews.controller;


import ua.pp.lazin.slownews.model.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/news")
public class News extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<NewsItem> newsList = NewsStorage.getInstance().getNewsList();

        request.setAttribute("newsList", newsList);

        request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);

    }
}