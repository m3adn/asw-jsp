/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package back_end.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.json.JSONObject;

import back_end.classes.user.User_Account;
import back_end.model.DAO;
import back_end.classes.transaction.Transaction;
import back_end.controller.classes.jwt.My_JWT;
import back_end.controller.classes.request.Coin_Info;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Buy", urlPatterns = {"/Servlet/Coins/Buy"})
public class Buy extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse res)
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
            }
            
            String ID = new My_JWT().verify(token);
            if (ID.equals("")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            
            User_Account userBuyer = dao.getUserData(ID);
            if (userBuyer.hasAtributes()) {
                float balance = userBuyer.getBalance();
                
                Transaction sell = dao.getTransaction(req.getParameter("SellID"));
                if (sell.hasAtributes()) {
                    float cost = new Coin_Info().getPrice(sell.getCoin(), sell.getUnits());
                    if (balance > cost) {
                        sell.setBuyer(Integer.parseInt(ID));
                        if (dao.insertTransactionDone(sell)
                                && dao.deleteTransaction(req.getParameter("SellID"))
                                && dao.changeBalance(sell.getBuyer(), -cost)
                                && dao.changeBalance(sell.getSeller(), cost)) {
                            res.setStatus(HttpServletResponse.SC_OK);
                        }
                    } else {
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    }
                } else {
                    res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                }
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            }
        } catch (JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            
            new My_JWT().verify(token);
            
            ArrayList<Transaction> transactions = dao.getTransactions();
            if (!transactions.isEmpty()) {
                PrintWriter out = res.getWriter();
                JSONObject obj = new JSONObject();

                ArrayList<String> ar = new ArrayList<>();
                int size = transactions.size();
                for (short i = 0; i < size; i++) {
                    obj.clear();
                    obj.put("ID", transactions.get(i).getID());
                    obj.put("Seller", transactions.get(i).getSeller());
                    obj.put("DateTime", transactions.get(i).getDateTime());
                    obj.put("Coin", transactions.get(i).getCoin());
                    obj.put("Units", transactions.get(i).getUnits());
                    ar.add(obj.toString());
                }
                res.setContentType("application/json");
                res.setStatus(HttpServletResponse.SC_OK);
                out.print(ar);
                out.flush();
            } else {
                res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                res.sendRedirect("transactions.jsp"); // location -> transactions
            }
        } catch (JWTVerificationException ex) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("index.jsp"); // location -> login
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            res.sendRedirect("transactions.jsp"); // location -> login
        }
    }
}
