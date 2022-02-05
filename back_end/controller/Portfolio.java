/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.json.JSONArray;
import org.json.JSONObject;

import back_end.model.DAO;
import back_end.classes.transaction.Transaction;
import back_end.controller.classes.My_JWT;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Portfolio", urlPatterns = {"/Servlet/Portfolio"})
public class Portfolio extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest req ,HttpServletResponse res)
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
            
            ArrayList<Transaction> portfolio = dao.getPortfolio(ID);
            if (!portfolio.isEmpty()) {
                PrintWriter out = res.getWriter();
                JSONArray array = new JSONArray();
                JSONObject obj = new JSONObject();

                int size = portfolio.size();
                for (short i = 0; i < size; i++) {
                    obj.put("ID", portfolio.get(i).getID());
                    obj.put("DateTime", portfolio.get(i).getDateTime());
                    obj.put("Coin", portfolio.get(i).getCoin());
                    obj.put("Units", portfolio.get(i).getUnits());

                    array.put(obj);
                    obj.clear();
                }
                res.setContentType("application/json");
                res.setStatus(HttpServletResponse.SC_OK);
                out.print(array);
                out.flush();
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
