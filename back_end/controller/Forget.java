/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.util.Properties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.Transport;

import back_end.model.DAO;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Forget", urlPatterns = {"/Servlet/Login/Forget"})
public class Forget extends HttpServlet {
    private DAO dao = new DAO();
    private final String from = "pmestre@utad.pt";
    private final String host = "localhost";

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (dao.existsUser(req.getParameter("Email"))) {
            String to = req.getParameter("Email");
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(properties);
            
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Recover account");
                message.setText("Email of recuperation", "UTF-8");
                
                Transport.send(message);
                
                res.setStatus(HttpServletResponse.SC_OK);
                res.sendRedirect("recoverUser.jsp"); // location -> recoverUser
            } catch (MessagingException ex) {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("forget.jsp"); // location -> forget
            }
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.sendRedirect("recoverUser.jsp"); // location -> recoverUser
        }
    }
}
