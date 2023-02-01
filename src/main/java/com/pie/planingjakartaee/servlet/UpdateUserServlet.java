package com.pie.planingjakartaee.servlet;

import com.pie.planingjakartaee.dao.DaoFactory;
import com.pie.planingjakartaee.dao.UserDao;
import com.pie.planingjakartaee.dao.entity.Role;
import com.pie.planingjakartaee.dao.entity.User;
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

@WebServlet( urlPatterns = "/user/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        String idString = req.getParameter("id");

        int sessionIdRole = sessionUser.getRole().getId();
        req.setAttribute("myRole", sessionIdRole);

        try {
            int id = Integer.parseInt(idString);
            Optional<User> userOptional = DaoFactory.getUserDao().get(id);

            if (userOptional.isPresent()) {
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
                //Récupére les roles
                List<Role> roles= DaoFactory.getRoleDao().getAll();
                req.setAttribute("roles", roles);
            }
        } catch (Exception e) {

        }
        req.getRequestDispatcher("/WEB-INF/userAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        boolean notError = true;

        String idStr = req.getParameter("id");
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

        //Formatage date
        String birthdateString = req.getParameter("birthdate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate birthdate = LocalDate.parse(birthdateString, formatter);


        //Hash password
        String password = req.getParameter("password");
        //String modifyDate = req.getParameter("modifyDate");

        //Activate account
        String activateString = req.getParameter("activate");
        //checkbox "" or null
        boolean activate = activateString != null;

        String idRoleString = req.getParameter("idRole");
        int idRole = 0;
        Optional<Role> role = Optional.empty();

        try {
            idRole = Integer.parseInt(idRoleString);
            role = DaoFactory.getRoleDao().get(idRole);
        } catch (Exception e) {
            System.out.println("idRole error parse int :" + e);
            notError = false;
        }

        if (notError){
            try {
                int id = Integer.parseInt(idStr);
                UserDao dao = DaoFactory.getUserDao();
                Optional<User> user = dao.get(id);

                if (user.isPresent()) {
                    User currentUser = user.get();
                    currentUser.setPseudo(pseudo);
                    currentUser.setEmail(email);
                    currentUser.setLastName(lastName);
                    currentUser.setFirstName(firstName);
                    currentUser.setAvatar(avatar);
                    currentUser.setBirthdate(birthdate);
                    currentUser.setPhone(phone);

                    if (!password.isEmpty()) {
                        currentUser.setPassword(password);
                    }

                    currentUser.setActivate(activate);
                    currentUser.setStreet(street);
                    currentUser.setCity(city);
                    currentUser.setCountry(country);
                    currentUser.setZip(zip);
                    currentUser.setRole(role.get());

                    try {
                        DaoFactory.getUserDao().update(currentUser);
                    } catch (Exception e) {
                        System.out.println("User not updated :" + e);
                    }
                }

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        resp.sendRedirect("/users");
    }
}
