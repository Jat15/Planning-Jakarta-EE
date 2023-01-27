package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.UserDao;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class ListUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = DaoFactory.getUserDao();
        List<User> userList = dao.getAll();
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/usersList.jsp").forward(req, resp);

    }
}
