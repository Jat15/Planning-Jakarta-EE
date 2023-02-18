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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/user/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        List<String> listErrors = new ArrayList<>();
        int sessionIdRole = sessionUser.getRole().getId();

        req.setAttribute("myRole", sessionIdRole);

        try {
            List<Role> roles = DaoFactory.getRoleDao().getAll();
            req.setAttribute("roles", roles);
        } catch (Exception e) {
            System.out.println("No acces DB table roles :" + e);
            listErrors.add("Unable to retrieve the list of roles from the database");
        }

        if (listErrors.size() == 0) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/userAdd.jsp");
            rd.forward(req,resp);
        } else {
            session.setAttribute("errors", listErrors);

            resp.sendRedirect("/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listErrors = new ArrayList<>();
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        int sessionIdRole = sessionUser.getRole().getId();

        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String avatar = req.getParameter("avatar");
        String birthdateString = req.getParameter("birthdate");
        String phone = req.getParameter("phone");
        String password =  BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt());
        String activateString = req.getParameter("activate");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String zip = req.getParameter("zip");
        String idRoleString = req.getParameter("idRole");

        boolean activate = false;
        try {
            //checkbox "" or null
            activate = activateString != null;
        } catch (Exception e) {
            System.err.println("Unable to convert the checkbox to a boolean value : " + e );
            listErrors.add("Unable to convert the checkbox to a boolean value");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate birthdate = null;
        if (listErrors.size() == 0) {
            try {
                birthdate = LocalDate.parse(birthdateString, formatter);
            } catch (Exception e) {
                System.err.println("Unable to convert the birthday date : " + e);
                listErrors.add("Unable to convert the birthday date");
            }
        }

        int idRole = 0;
        if (listErrors.size() == 0) {
            try {
                idRole = Integer.parseInt(idRoleString);
            } catch (Exception e) {
                System.err.println("Unable to convert string ID to int : " + e);
                listErrors.add("Unable to convert string ID to int");
            }
        }


        if (listErrors.size() == 0) {
            if(!(idRole > 0 && idRole < sessionIdRole)) {
                System.err.println("You are not authorized to create this user");
                listErrors.add("You are not authorized to create this user");
            }
        }

        Optional<Role> role = Optional.empty();
        if (listErrors.size() == 0) {
            try {
                role = DaoFactory.getRoleDao().get(idRole);
            } catch (Exception e) {
                System.err.println("Unable to retrieve the name of the role from the database : " + e);
                listErrors.add("Unable to retrieve the name of the role from the database");
            }
        }

        if (listErrors.size() == 0) {
            if(role.isEmpty()) {
                System.err.println("No role found");
                listErrors.add("No role found");
            }
        }

        User newUser = new User();
        if (listErrors.size() == 0) {
            try {
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
            } catch(Exception e) {
                System.err.println("Unable to create user with the given data : " + e);
                listErrors.add("Unable to create user with the given data");
            }
        }

        if (listErrors.size() == 0) {
            try {
                DaoFactory.getUserDao().create(newUser);
            } catch (Exception e) {
                System.err.println("Unable to save the user : " + e);
                listErrors.add("Unable to save the user");
            }
        }

        if (!listErrors.isEmpty()) {
            session.setAttribute("errors", listErrors);
        } else {
            session.setAttribute("success", "The user has been created");
        }

        resp.sendRedirect("/users");
    }
}
