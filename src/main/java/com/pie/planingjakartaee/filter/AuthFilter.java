package com.pie.planingjakartaee.filter;

import com.pie.planingjakartaee.dao.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/users","/user/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        boolean acces = false;
        if (sessionUser != null ) {
            acces = sessionUser.getRole().getId() > 1;
        }

        if (acces) {
            //For navbar my account
            req.setAttribute("sessionIdUser", sessionUser.getId());
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
    }
}
