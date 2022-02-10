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
@WebServlet(name = "TransactionsAdmin", urlPatterns = {"/Servlet/Admin/Transactions"})
public class Transactions extends HttpServlet {
    private DAO dao = new DAO();
    
    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // verify account
        if (dao.verifyTransaction(req.getParameter("transactionID"))) {
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("transactiontAdmin.jsp"); // location -> transactiontAdmin
        } else {
            res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            res.sendRedirect("transactiontAdmin.jsp"); // location -> transactiontAdmin
        }
    }
}
