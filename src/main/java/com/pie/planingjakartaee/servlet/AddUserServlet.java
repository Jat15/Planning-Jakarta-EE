package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/user/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/userAdd.jsp");

        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String id = req.getParameter("id");

        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String avatar = req.getParameter("avatar");

        String birthdateString = req.getParameter("birthdate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate birthdate =  LocalDate.parse(birthdateString, formatter);

        String phone = req.getParameter("phone");

        String password = req.getParameter("password");
        //String createDate = req.getParameter("createDate");
        //String modifyDate = req.getParameter("modifyDate");

        //Activate account
        String activateString = req.getParameter("activate");
        Boolean activate = Boolean.valueOf(activateString);
        //Adress
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String zip = req.getParameter("zip");

        User newUser = new User();

        newUser.setPseudo(pseudo);
        newUser.setEmail(email);
        newUser.setLastName(lastName);
        newUser.setFirstName(firstName);
        newUser.setAvatar(avatar);
        newUser.setBirthdate(birthdate);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setActivate(activate);
        newUser.setStreet(street);
        newUser.setCity(city);
        newUser.setCountry(country);
        newUser.setZip(zip);

        DaoFactory.getUserDao().create(newUser);

    }
}
