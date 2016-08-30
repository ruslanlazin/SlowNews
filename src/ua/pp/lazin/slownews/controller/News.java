
package ua.pp.lazin.slownews.controller;


import ua.pp.lazin.slownews.model.Storage;
import ua.pp.lazin.slownews.model.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Laz on 24.08.2016.
 */
@WebServlet("/news")
public class News extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user1 = new User();
        user1.setLogin("vas");
        User user2 = new User();
        user2.setLogin("vasvas");
        User user3 = new User();
        user3.setLogin("vasvasvas");

        List<User> users = new ArrayList<User>();

        users.add(user1);
        users.add(user3);
        users.add(user2);

       // Storage.addToMap("t", users);
        //Storage.addToMap(request, users.size() );

        System.out.println(request.hashCode());

        request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);

    }
}