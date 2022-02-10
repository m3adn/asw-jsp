/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import back_end.model.DAO;
import back_end.controller.classes.My_JWT;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Login", urlPatterns = {"/Servlet/Login"})
public class Login extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {      
        if (dao.verifyUser(req.getParameter("Email"), req.getParameter("Password"))) {
            String ID = dao.getID(req.getParameter("Email"));
            if (ID.equals("")) {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            String token = new My_JWT().createToken(ID);
            Cookie cookie = new Cookie("token", token);
            res.addCookie(cookie);
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("main.jsp"); // location -> main.jsp
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // location -> login
        }
    }
}
