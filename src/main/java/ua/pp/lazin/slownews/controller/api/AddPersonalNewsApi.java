package ua.pp.lazin.slownews.controller.api;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.entity.Users;
import ua.pp.lazin.slownews.persistance.UserDaoList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet("/api/addPersonalNews")
public class AddPersonalNewsApi extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            response.getWriter().println("Acceptable only for registered users. Please sign in first");
            return;
        }
        String uri = request.getParameter("newsUri");
        List<NewsItem> newsList = (List<NewsItem>) request.getSession().getAttribute("newsList");
        for (NewsItem newsItem : newsList) {
            if (newsItem.getUri().equals(uri)) {
                newsItem.setFavorite(true);
                user.getPersonalNews().add(newsItem);
                response.setStatus(200);
                response.getWriter().print("The news has been successfully added to your personal archive");
                return;
            }
        }
        response.setStatus(404);
        response.getWriter().println("The news with URI=" + uri + " has not been found. Please refresh page (F5) and try again");
    }

}

