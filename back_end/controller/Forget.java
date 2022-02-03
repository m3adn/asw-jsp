/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import back_end.model.DAO;
import back_end.controller.classes.Modem;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Forget", urlPatterns = {"/Servlet/Login/Forget"})
public class Forget extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (dao.existsUser(req.getParameter("Email"))) {
            // send email to recover
            String msg = "Code: " + Math.abs(new Random().nextInt());
            Modem modem = new Modem();
            modem.sendMessage(Integer.parseInt(req.getParameter("PhoneNumber")), msg);
            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("recoverUser.jsp"); // location -> recoverUser.jsp
        }
        res.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
