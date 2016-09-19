
package ua.pp.lazin.slownews.controller;


import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.persistance.UserDao;
import ua.pp.lazin.slownews.persistance.UserDaoList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Laz on 24.08.2016.
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {

    private UserDao userDao = UserDaoList.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("Username");
        if (!userDao.isLoginUnique(login)) {
            request.setAttribute("message", "User with username " + login + " already exist");
            request.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(request, response);
        }

        String email = request.getParameter("Email");
        if (!userDao.isEmailUnique(email)) {
            request.setAttribute("message", "User with e-mail " + email + " already exist");
            request.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(request, response);
        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(request.getParameter("Password"));
        user.setFirstName(request.getParameter("FirstName"));
        user.setLastName(request.getParameter("LastName"));
        user.setEmail(email);

        userDao.saveUser(user);


        request.getRequestDispatcher("/WEB-INF/pages/registrationOK.jsp").forward(request, response);
    }
}