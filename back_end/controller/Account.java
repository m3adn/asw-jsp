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
import com.auth0.jwt.exceptions.JWTVerificationException;

import back_end.classes.user.User_Account;
import back_end.model.DAO;
import back_end.controller.classes.My_JWT;

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
                                                req.getParameter("ReferralCode"));
            
            if (dao.insertUser(newUser)) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.sendRedirect("index.jsp"); // location -> login
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("resgister.jsp"); // location -> register
            }
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("register.jsp"); // location -> register
        }
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
            } else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            String ID = new My_JWT().verify(token);
            if (ID.equals("")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            User_Account userData = dao.getUserData(ID);
            if (userData.hasAtributes()) {
                PrintWriter out = res.getWriter();
                JSONObject obj = new JSONObject();
                obj.put("ID", userData.getId());
                obj.put("Email", userData.getEmail());
                obj.put("Username", userData.getUsername());
                obj.put("PhoneNumber", userData.getPhoneNumber());
                obj.put("Balance", userData.getBalance());
                obj.put("Admin", userData.isAdmin());

                res.sendRedirect("user.jsp"); // location -> user
                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                out.print(obj);
                out.flush();
                
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("user.jsp"); // location -> user
            }
        } catch (JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // location -> login
        }
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
            } else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            String ID = new My_JWT().verify(token);
            if (ID.equals("")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.sendRedirect("index.jsp"); // location -> login
            }
            
            User_Account updateUser = new User_Account(req.getParameter("Email"),
                    req.getParameter("Username"),
                    req.getParameter("Password"),
                    req.getParameter("PhoneNumber"),
                    "");
            
            if (dao.updateUserData(updateUser, ID)) {
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("userUpdate.jsp"); // locaion -> userUpdate
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("userUpdate.jsp"); // locaion -> userUpdate
            }
        } catch (JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // locaion -> userUpdate
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("userUpdate.jsp"); // locaion -> userUpdate
        }
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
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        res.addCookie(cookie);
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
            
            if (dao.deleteUser(ID)) {
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("index.jsp"); // location -> login
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("index.jsp"); // location -> login
            }
        } catch(JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // location -> login
        }
    }
}