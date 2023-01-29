package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.Role;
import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/user/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        boolean accesPage = false;
        if (sessionUser != null ) {
            accesPage = sessionUser.getRole().getId() > 1;
        }

        if (accesPage) {
            boolean noErrors = true;
            int sessionIdRole = sessionUser.getRole().getId();

            req.setAttribute("myRole", sessionIdRole);

            try {
                List<Role> roles= DaoFactory.getRoleDao().getAll();
                req.setAttribute("roles", roles);
            } catch (Exception e) {
                System.out.println("No acces DB table roles :" + e);
                noErrors = false;
            }

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/userAdd.jsp");
            rd.forward(req,resp);
        } else {
            resp.sendRedirect("/");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        boolean accesPage = false;
        if (sessionUser != null ) {
            accesPage = sessionUser.getRole().getId() > 1;
        }

        if (accesPage) {
            boolean noErrors = true;
            int sessionIdRole = sessionUser.getRole().getId();

            String pseudo = req.getParameter("pseudo");
            String email = req.getParameter("email");
            String lastName = req.getParameter("lastName");
            String firstName = req.getParameter("firstName");
            String avatar = req.getParameter("avatar");

            String birthdateString = req.getParameter("birthdate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate birthdate =  LocalDate.parse(birthdateString, formatter);

            String phone = req.getParameter("phone");

            //Hash password
            String password = req.getParameter("password");
            //String createDate = req.getParameter("createDate");
            //String modifyDate = req.getParameter("modifyDate");


            //Activate account
            String activateString = req.getParameter("activate");
            //checkbox "" or null
            boolean activate = activateString != null;

            //Adress
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String country = req.getParameter("country");
            String zip = req.getParameter("zip");

            String idRoleString = req.getParameter("idRole");
            int idRole = 0;
            Optional<Role> role = Optional.empty();

            try {
                idRole = Integer.parseInt(idRoleString);
                noErrors = idRole >0 && idRole < sessionIdRole;
                if (noErrors) {
                    role = DaoFactory.getRoleDao().get(idRole);
                    noErrors = role.isPresent();
                }
            } catch (Exception e) {
                System.out.println("idRole error parse int :" + e);
                noErrors = false;
            }



            if (noErrors){

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
                newUser.setRole(role.get());

                try {
                    DaoFactory.getUserDao().create(newUser);
                } catch (Exception e) {
                    noErrors = false;
                    System.out.println("User not create :" + e);
                }
            }

            if (noErrors) {
                resp.sendRedirect("/users");
            } else {
                resp.sendRedirect("/user/add");
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}
