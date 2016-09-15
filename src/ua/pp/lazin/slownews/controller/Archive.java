
package ua.pp.lazin.slownews.controller;


import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.integration.NewsCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/archive")
public class Archive extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.setAttribute("message", "To use personal Archive please Sign in first");
            request.getSession().removeAttribute("newsList");
            request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);

            return;
        }

        List<NewsItem> newsList = user.getPersonalNews();

        request.getSession().setAttribute("newsList", newsList);

        if (newsList == null) {
            request.setAttribute("message", "You still haven't got any favorite news");
        }
        request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);

    }
}