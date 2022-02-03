/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import back_end.model.DAO;

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
            Algorithm alg = Algorithm.HMAC256("ASW");
            Map<String, Object> payload = new HashMap<>();
            payload.put("ID", dao.getID(req.getParameter("Email")));
            String token = JWT.create()
                    .withPayload(payload)
                    .sign(alg);

            Cookie cookie = new Cookie("token", token);
            res.addCookie(cookie);
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("main.jsp"); // location -> main.jsp
        }
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
