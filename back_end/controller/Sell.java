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

import com.auth0.jwt.exceptions.JWTVerificationException;

import back_end.classes.request.Request_Coins;
import back_end.controller.classes.My_JWT;
import back_end.model.DAO;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Sell", urlPatterns = {"/Servlet/Coins/Sell"})
public class Sell extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String token = "";
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Token")) {
                        token = cookie.getValue();
                    }
                }
            } else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            String ID = new My_JWT().verify(token);
            if (ID.equals("")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            Request_Coins reqCoins = new Request_Coins(req.getParameter("Coin"),
                    Float.parseFloat(req.getParameter("Units")));

            if (dao.sellCoins(ID, reqCoins)) {
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("transactions.jsp"); // location -> transactions
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("index.jsp"); // location -> login
            }
        } catch (JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // location -> login
        }
    }
}
