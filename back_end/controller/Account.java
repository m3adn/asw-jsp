/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.json.JSONObject;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.cdimascio.dotenv.Dotenv;

import back_end.classes.user.User_Account;
import back_end.controller.classes.Modem;
import back_end.model.DAO;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Account", urlPatterns = {"/Servlet/Account"})
public class Account extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            User_Account newUser = new User_Account(req.getParameter("Email"),
                                                req.getParameter("Username"),
                                                req.getParameter("Password"),
                                                req.getParameter("PhoneNumber"),
                                                req.getParameter("ReferralCode"),
                                                req.getParameter("Admin"));
            
            if (dao.insertUser(newUser)) {
                String msg = "Welcome " + req.getParameter("Username");
                Modem modem = new Modem();
                modem.sendMessage(Integer.parseInt(req.getParameter("PhoneNumber")), msg);
                
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.sendRedirect("login.jsp"); // location -> login.jsp
            }
        } catch (IllegalArgumentException ex) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse res)
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
            }
            Algorithm algorithm = Algorithm.HMAC256(Dotenv.load().get("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            
            JSONObject payload = new JSONObject(verifier.verify(token).getPayload());
            
            User_Account userData = dao.getUserData(payload.getString("ID"));
            if (userData.hasAtributes()) {
                PrintWriter out = res.getWriter();
                JSONObject obj = new JSONObject();
                obj.put("ID", userData.getId());
                obj.put("Email", userData.getEmail());
                obj.put("Username", userData.getUsername());
                obj.put("PhoneNumber", userData.getPhoneNumber());
                obj.put("Balance", userData.getBalance());
                obj.put("Admin", userData.isAdmin());

                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                out.print(obj);
                out.flush();
            }
        } catch (JWTVerificationException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

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
            }
            Algorithm algorithm = Algorithm.HMAC256(Dotenv.load().get("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            
            JSONObject payload = new JSONObject(verifier.verify(token).getPayload());
            
            User_Account updateUser = new User_Account(req.getParameter("Email"),
                    req.getParameter("Username"),
                    req.getParameter("Password"),
                    req.getParameter("PhoneNumber"),
                    "",
                    "0");
            
            if (dao.updateUserData(updateUser, payload.getString("ID"))) {
                res.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (JWTVerificationException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (IllegalArgumentException ex) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }
    
    @Override
    protected void doDelete (HttpServletRequest req, HttpServletResponse res)
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
            }
            Algorithm algorithm = Algorithm.HMAC256(Dotenv.load().get("JWT_SECRET"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            
            JSONObject payload = new JSONObject(verifier.verify(token).getPayload());
            
            if (dao.deleteUser(payload.getString("ID"))) {
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("login.jsp"); // location -> login.jsp
            }
        } catch(JWTVerificationException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }
}