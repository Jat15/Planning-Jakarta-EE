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

@WebServlet(urlPatterns = "/users")
public class ListUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listErrors = new ArrayList<>();
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        int sessionIdRole = sessionUser.getRole().getId();

        List<User> userList = null;
        try {
            UserDao dao = DaoFactory.getUserDao();
            userList = dao.getAllByRole(sessionIdRole);
        } catch (Exception e) {
            System.err.println("No access user list with id" + e);
            listErrors.add("Unable to access the database");
        }

        List<String> listOldErrors = (List<String>) session.getAttribute("errors");
        if (listOldErrors != null) {
            session.removeAttribute("errors");
            listErrors.addAll(listOldErrors);
        }

        if (!listErrors.isEmpty()) {
            req.setAttribute("errors", listErrors);
        }

        String sessionSuccess = (String) session.getAttribute("success");
        if (sessionSuccess != null) {
            req.setAttribute("success", sessionSuccess);
            session.removeAttribute("success");
        }

        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/usersList.jsp").forward(req, resp);
    }
}
