package ua.pp.lazin.slownews.controller.api;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.persistance.UserDao;
import ua.pp.lazin.slownews.persistance.UserDaoList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/removePersonalNews")
public class RemovePersonalNewsApi extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            response.getWriter().println("Your session has timed out. Please sign in again.");
            return;
        }
        String uri = request.getParameter("newsUri");
        List<NewsItem> newsList = user.getPersonalNews();
        for (NewsItem newsItem : newsList) {
            if (newsItem.getUri().equals(uri)) {
                user.getPersonalNews().remove(newsItem);
                response.setStatus(200);
                response.getWriter().print("The news has been successfully removed from your personal archive");
                return;
            }
        }
        response.setStatus(404);
        response.getWriter().println("The news with URI=" + uri + " has not been found. Please refresh page (F5) and try again");
    }

}

