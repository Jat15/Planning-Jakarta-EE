package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet( urlPatterns = "/user/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        try {
            int id = Integer.parseInt(idString);
            Optional<User> userOptional = DaoFactory.getUserDao().get(id);

            if (userOptional.isPresent()) {

                req.setAttribute(userOptional.get().getPseudo(),"pseudo");
                req.setAttribute(userOptional.get().getEmail(),"email");
                req.setAttribute(userOptional.get().getLastName(),"lastName");
                req.setAttribute(userOptional.get().getFirstName(),"firstName");
                req.setAttribute(userOptional.get().getAvatar(),"avatar");
                //req.setAttribute(userOptional.get().getBirthdate(),"birthdate");
                req.setAttribute(userOptional.get().getPhone(),"phone");
                //req.setAttribute(userOptional.get().getPassword(),"");
                //req.setAttribute(userOptional.get().getActivate(),"activate");
                req.setAttribute(userOptional.get().getStreet(),"street");
                req.setAttribute(userOptional.get().getCity(),"city");
                req.setAttribute(userOptional.get().getCountry(),"country");
                req.setAttribute(userOptional.get().getZip(),"zip");


            }





        } catch (Exception e) {

        }

    }
}
