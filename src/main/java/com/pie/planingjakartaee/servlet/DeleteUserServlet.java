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
import java.util.Optional;

@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean noErrors = true;
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        String idStr = req.getParameter("id");
        int sessionIdRole = sessionUser.getRole().getId();

        try {
            int id = Integer.parseInt(idStr);
            UserDao dao = DaoFactory.getUserDao();
            Optional<User> user = dao.get(id);

            if (user.isPresent()) {
                int idRole = user.get().getRole().getId();
                noErrors = idRole > 0 && idRole < sessionIdRole;

                if (noErrors) {
                    UserDao dao1 = DaoFactory.getUserDao();
                    dao1.delete(user.get());
                }
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
