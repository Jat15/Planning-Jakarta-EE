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



                req.setAttribute("pseudo", userOptional.get().getPseudo());
                req.setAttribute("email", userOptional.get().getEmail());
                req.setAttribute("lastName", userOptional.get().getLastName());
                req.setAttribute("firstName", userOptional.get().getFirstName());
                req.setAttribute("avatar", userOptional.get().getAvatar());

                //A revoir
                req.setAttribute("birthdate", userOptional.get().getBirthdate());
                
                req.setAttribute("phone", userOptional.get().getPhone());

                //Pas besoins
                //req.setAttribute(userOptional.get().getPassword(),"");
                //Update
                //req.setAttribute(userOptional.get().getActivate(),"activate");

                req.setAttribute("street", userOptional.get().getStreet());
                req.setAttribute("city", userOptional.get().getCity());
                req.setAttribute("country", userOptional.get().getCountry());
                req.setAttribute("zip", userOptional.get().getZip());


            }





        } catch (Exception e) {

        }

        req.getRequestDispatcher("/WEB-INF/userAdd.jsp").forward(req, resp);

    }
}
