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
import jakarta.mail.MessagingException;

import back_end.model.DAO;
import back_end.controller.classes.email.Email;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Forget", urlPatterns = {"/Servlet/Login/Forget"})
public class Forget extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (dao.existsUser(req.getParameter("Email"))) {
            try {
                if (new Email().sendEmail(req.getParameter("Email"))) {
                    res.setStatus(HttpServletResponse.SC_OK);
                    res.sendRedirect("../../login.jsp"); // location -> forget
                } else {
                    res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    res.sendRedirect("../../acc-recovery.jsp"); // location -> forget
                }
            } catch (MessagingException ex) {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("../../acc-recovery.jsp"); // location -> forget
            }
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.sendRedirect("../../acc-recovery.jsp"); // location -> forget
        }
    }
}
