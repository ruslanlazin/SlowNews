package ua.pp.lazin.slownews.controller.api;

import ua.pp.lazin.slownews.entity.Users;
import ua.pp.lazin.slownews.dao.DaoFactory;

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

@WebServlet("/api/users")
public class UsersApi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users users = new Users();
        users.setUsers(DaoFactory.getUserDao().getAll());

        Writer out = response.getWriter();
        response.setContentType("application/xml");
        response.setCharacterEncoding("utf-8");

        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в response
            marshaller.marshal(users, out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}

