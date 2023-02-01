package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean connected = false;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> user = Optional.empty();

        try{
            user = DaoFactory.getUserDao().getPassword(email);
        } catch (Exception e) {
            System.out.println("No acces DB :"+ e);
        }

        HttpSession session = req.getSession();

        if (user.isPresent()) {
            //verify password
            if (password.equals(user.get().getPassword())){
                connected = user.get().getRole().getId() > 1;
                connected = user.get().isActivate() && connected;
            }
        }

        if (connected) {
            User newUser = user.get();
            session.setAttribute("user", newUser);

            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/");
        }
    }
}
