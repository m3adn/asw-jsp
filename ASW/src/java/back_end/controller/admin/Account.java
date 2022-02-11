/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import back_end.classes.user.User_Account;
import back_end.model.DAO;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "AccountAdmin", urlPatterns = {"/Servlet/Admin/Account"})
public class Account extends HttpServlet {
    private DAO dao = new DAO();
    
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            // verify account
            User_Account newUser = new User_Account (req.getParameter("Email"),
                                                req.getParameter("Username"),
                                                req.getParameter("Password"),
                                                req.getParameter("PhoneNumber"),
                                                req.getParameter("ReferralCode"));
            
            if (dao.insertUser(newUser)){
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.sendRedirect("createAdmin.jsp"); // location -> createAdmin
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("index.jsp"); // location -> login
            }
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("createAdmin.jsp"); // location -> createAdmin
            
        }   
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // verify account
        User_Account userData = dao.getUserData(req.getParameter("ID"));
        PrintWriter out = res.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("Email", userData.getEmail());
        obj.put("Username", userData.getUsername());
        obj.put("PhoneNumber", userData.getPhoneNumber());
        obj.put("ReferralCode", userData.getReferralCode());
        res.setContentType("application/json");
        out.print(obj);
        out.flush();
        res.setStatus(HttpServletResponse.SC_OK);
        res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
    }

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            // verify account
            User_Account updateUser = new User_Account(req.getParameter("Email"),
                                                req.getParameter("Username"),
                                                req.getParameter("Password"),
                                                req.getParameter("PhoneNumber"),
                                                req.getParameter("ReferralCode"));
            
            if (dao.updateUserData(updateUser, req.getParameter("ID"))) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
            }
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
        }
    }
    
    @Override
    protected void doDelete (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // verify account
        if (dao.deleteUser(req.getParameter("ID"))){
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
        } else {
            res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            res.sendRedirect("accountAdmin.jsp"); // location -> accountAdmin
        }
    }
}
