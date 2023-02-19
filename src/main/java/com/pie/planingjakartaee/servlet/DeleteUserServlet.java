package com.pie.planingjakartaee.servlet;


import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.UserDao;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> listErrors = new ArrayList<>();
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        String idStr = req.getParameter("id");
        int sessionIdRole = sessionUser.getRole().getId();

        int id = 0;

        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            System.err.println("Invalid ID : " + e );
            listErrors.add("Unable to parse integer for invalid ID");
        }

        Optional<User> user = Optional.empty();
        if (listErrors.size() == 0) {
            try {
                UserDao dao = DaoFactory.getUserDao();
                user = dao.get(id);
            } catch (Exception e) {
                System.err.println("Unable to access the database :" + e);
                listErrors.add("Unable to access the database");
            }
        }

        if (listErrors.size() == 0) {
            if (user.isEmpty()) {
                System.err.println("No user found with the given ID");
                listErrors.add("No user found with the given ID");
            }
        }

        if (listErrors.size() == 0) {
            int idRole = user.get().getRole().getId();
            if (!(idRole > 0 && idRole < sessionIdRole)) {
                System.err.println("You are not authorized to delete this user");
                listErrors.add("You are not authorized to delete this user");
            }
        }


        if (listErrors.size() == 0) {
            try {
                UserDao dao1 = DaoFactory.getUserDao();
                dao1.delete(user.get());
            } catch (Exception e) {
                listErrors.add("Unable to delete this user");
                System.err.println("Unable to delete this user : " + e);
            }
        }

        if (!listErrors.isEmpty()) {
            session.setAttribute("errors", listErrors);
        } else {
            session.setAttribute("success", "The user has been deleted");
        }

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
