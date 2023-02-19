package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.UserDao;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/users/find")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listErrors = new ArrayList<>();
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        String search = req.getParameter("search");

        int sessionIdRole = sessionUser.getRole().getId();

        List<User> userList = null;
        try {
            UserDao dao = DaoFactory.getUserDao();
            userList = dao.getAllFind(sessionIdRole, search);
        } catch (Exception e) {
            System.err.println("No access user list" + e);
            listErrors.add("Unable to access the database");
        }


        if (!listErrors.isEmpty()) {
            req.setAttribute("errors", listErrors);
        }


        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/usersList.jsp").forward(req, resp);
    }
}
