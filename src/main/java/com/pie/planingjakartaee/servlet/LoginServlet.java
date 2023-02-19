package com.pie.planingjakartaee.servlet;


import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> errors = (List<String>) session.getAttribute("errors");

        if (errors != null) {
            session.removeAttribute("errors");
            req.setAttribute("errors", errors);
        }

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean connected = false;
        List<String> listErrors = new ArrayList<>();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> user = Optional.empty();

        try{
            user = DaoFactory.getUserDao().getPassword(email);
        } catch (Exception e) {
            System.out.println("Unable to access the database :" + e);
            listErrors.add("Unable to access the database");
        }

        HttpSession session = req.getSession();

        if (listErrors.size() == 0) {
            if (user.isPresent()) {
                String passwordDb = user.get().getPassword();
                if (BCrypt.checkpw(password, passwordDb)) {
                    connected = user.get().getRole().getId() > 1;
                    connected = user.get().isActivate() && connected;
                } else {
                    System.err.println("Bad password for : " + user.get().getEmail());
                    listErrors.add("The email or password is incorrect.");
                }
            } else {
                System.err.println("Email not exist in DB : " + email);
                listErrors.add("The email or password is incorrect.");
            }
        }
        
        if (connected) {
            User newUser = user.get();
            session.setAttribute("user", newUser);

            resp.sendRedirect("/users");
        } else {
            session.setAttribute("errors", listErrors);

            resp.sendRedirect("/");
        }
    }
}
