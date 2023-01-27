package com.pie.planingjakartaee.servlet;


import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.UserDao;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);
            UserDao dao = DaoFactory.getUserDao();
            Optional<User> user = dao.get(id);

            if (user.isPresent()) {
                UserDao dao1 = DaoFactory.getUserDao();
                dao1.delete(user.get());
                resp.sendRedirect(req.getContextPath() + "/users");
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

    }
}
