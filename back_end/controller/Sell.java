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

import back_end.classes.request.Request_Coins;
import back_end.classes.user.User_Account;
import back_end.model.DAO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.Cookie;
import java.io.PrintWriter;
import org.json.JSONObject;

/**
 *
 * @author epilif3sotnas
 */
@WebServlet(name = "Sell", urlPatterns = {"/Servlet/Coins/Sell"})
public class Sell extends HttpServlet {
    private DAO dao = new DAO();

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
            
            Request_Coins reqCoins = new Request_Coins(req.getParameter("coin"),
                    Float.parseFloat(req.getParameter("units")));

            if (dao.sellCoins(payload.getString("ID"), reqCoins)) {
                res.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (JWTVerificationException ex) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }
}
