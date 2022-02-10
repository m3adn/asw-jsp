/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import back_end.model.DAO;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Withdraw", urlPatterns = {"/Servlet/Admin/Withdraw"})
public class Withdraw extends HttpServlet {
    DAO dao = new DAO();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // verify account
        if (dao.verifyWithdraw(req.getParameter("withdrawID"))) {
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("withdrawAdmin.jsp"); // location -> withdrawAdmin
        } else {
            res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            res.sendRedirect("withdrawAdmin.jsp"); // location -> withdrawAdmin
        }
    }
}
