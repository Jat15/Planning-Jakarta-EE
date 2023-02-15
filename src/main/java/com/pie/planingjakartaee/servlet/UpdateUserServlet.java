package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.entity.Role;
import com.pie.planingjakartaee.dao.entity.User;
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

@WebServlet( urlPatterns = "/user/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listErrors = new ArrayList<>();

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            listErrors.add("No session");
        }

        int sessionIdRole = 0;
        if (listErrors.size() == 0 ) {
            try {
                sessionIdRole = sessionUser.getRole().getId();
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("No id in session");
            }
        }

        int paramId = 0;
        if (listErrors.size() == 0 ) {
            try {
                String idString = req.getParameter("id");
                paramId = Integer.parseInt(idString);
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Id in param is not int");
            }
        }

        Optional<User> userOptional = Optional.empty();
        if (listErrors.size() == 0 ) {
            try {
                userOptional = DaoFactory.getUserDao().get(paramId);
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Request get by id error");
            }
        }

        if (listErrors.size() == 0 ) {
            if (userOptional.isPresent()) {
                if (!(userOptional.get().getRole().getId() < sessionIdRole)) {
                    if(userOptional.get().getId() != sessionUser.getId()) {
                        listErrors.add("You do not have authorization to view the page");
                    }
                }
            } else {
                listErrors.add("User is not in DB");
            }
        }

        if (listErrors.size() == 0) {
            try {
                req.setAttribute("id", userOptional.get().getId());
                req.setAttribute("pseudo", userOptional.get().getPseudo());
                req.setAttribute("email", userOptional.get().getEmail());
                req.setAttribute("lastName", userOptional.get().getLastName());
                req.setAttribute("firstName", userOptional.get().getFirstName());
                req.setAttribute("avatar", userOptional.get().getAvatar());
                req.setAttribute("birthdate", userOptional.get().getBirthdate());
                req.setAttribute("phone", userOptional.get().getPhone());
                //req.setAttribute("password", userOptional.get().getPassword());
                req.setAttribute("activate", userOptional.get().isActivate());
                req.setAttribute("street", userOptional.get().getStreet());
                req.setAttribute("city", userOptional.get().getCity());
                req.setAttribute("country", userOptional.get().getCountry());
                req.setAttribute("zip", userOptional.get().getZip());
                req.setAttribute("roleName", userOptional.get().getRole().getName());
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Set Attribute of data user is not possible");
            }
        }

        List<Role> roles = new ArrayList<>();
        if (listErrors.size() == 0) {
            try{
                roles = DaoFactory.getRoleDao().getAll();
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Set Attribute data user is not possible");
            }
        }

        req.setAttribute("roles", roles);
        req.setAttribute("myRole", sessionIdRole);

        for (String error: listErrors) {
            System.out.println(error);
        }

        req.getRequestDispatcher("/WEB-INF/userAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listErrors = new ArrayList<>();

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            listErrors.add("No session");
        }

        String idUserString = req.getParameter("id");
        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String avatar = req.getParameter("avatar");
        String phone = req.getParameter("phone");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String zip = req.getParameter("zip");
        String birthdateString = req.getParameter("birthdate");
        String password = req.getParameter("password");
        String activateString = req.getParameter("activate");
        String idRoleString = req.getParameter("idRole");

        LocalDate birthdate = LocalDate.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            birthdate = LocalDate.parse(birthdateString, formatter);
        } catch(Exception e) {
            System.err.println(e);
            listErrors.add("The birthdate cannot be converted");
        }

        boolean activate = false;
        if (listErrors.size() == 0){
            try {
                activate = activateString != null;
            } catch(Exception e) {
                System.err.println(e);
                listErrors.add("Transforming activate to a boolean value is not possible");
            }
        }

        int id = 0;
        if (listErrors.size() == 0) {
            try {
                id = Integer.parseInt(idUserString);
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Transforming id user to a integer value is not possible");
            }
        }

        Optional<User> user = Optional.empty();
        if (listErrors.size() == 0) {
            try {
                user = DaoFactory.getUserDao().get(id);
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Request for user id is invalid");
            }
        }

        if (listErrors.size() == 0) {
            if (user.isEmpty()) {
                listErrors.add("The user you are trying to update is not present in the database");
            }
        }

        int idRole = 0;
        if (listErrors.size() == 0) {
            if (idRoleString == null){
                idRole = user.get().getRole().getId();
            } else {
                try {
                    idRole = Integer.parseInt(idRoleString);
                } catch (Exception e) {
                    System.err.println(e);
                    listErrors.add("Transforming id role to a integer value is not possible");
                }
            }
        }

        Optional<Role> role = Optional.empty();
        if (listErrors.size() == 0) {
            try {
                role = DaoFactory.getRoleDao().get(idRole);
            } catch (Exception e) {
                System.out.println();
                listErrors.add("Request for role id is invalid");
            }
        }

        if (listErrors.size() == 0) {
            if (role.isEmpty()) {
                listErrors.add("The role you are trying to use for updating the user is not present in the database");
            }
        }

        if (listErrors.size() == 0 ) {
            if (!(role.get().getId() < sessionUser.getRole().getId())) {
                if(user.get().getId() != sessionUser.getId()) {
                    listErrors.add("You do not have authorization for update user");
                }
            }
        }

        User currentUser = new User();
        if (listErrors.size() == 0){
            currentUser = user.get();

            try {
                currentUser.setPseudo(pseudo);
                currentUser.setEmail(email);
                currentUser.setLastName(lastName);
                currentUser.setFirstName(firstName);
                currentUser.setAvatar(avatar);
                currentUser.setBirthdate(birthdate);
                currentUser.setPhone(phone);
                currentUser.setActivate(activate);
                currentUser.setStreet(street);
                currentUser.setCity(city);
                currentUser.setCountry(country);
                currentUser.setZip(zip);
                currentUser.setRole(role.get());

                if (!password.isEmpty()) {
                    String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
                    currentUser.setPassword(passwordHash);
                }

            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("Request for user id is invalid.");
            }
        }

        if (listErrors.size() == 0) {
            try {
                DaoFactory.getUserDao().update(currentUser);
            } catch (Exception e) {
                System.err.println(e);
                listErrors.add("The request to update the user encountered an error");
            }
        }

        for (String error: listErrors) {
            System.out.println(error);
        }

        resp.sendRedirect("/users");
    }
}
