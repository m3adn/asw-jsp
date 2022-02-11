/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Logout", urlPatterns = {"/Servlet/Logout"})
public class Logout extends HttpServlet {
    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            res.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
