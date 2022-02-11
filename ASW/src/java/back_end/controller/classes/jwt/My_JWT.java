/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author epilif3sotnas
 */
public class My_JWT {
    
    public String verify (String token) {
        Dotenv dotenv = Dotenv.configure()
                .directory("/home/epilif3sotnas/NetBeansProjects/ASW/.env")
                .ignoreIfMalformed()
                .load();
        Algorithm algorithm = Algorithm.HMAC256(dotenv.get("JWT_SECRET"));
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        return verifier.verify(JWT.decode(token)).getId();
    }
    
    public String createToken (String ID) {
        Dotenv dotenv = Dotenv.configure()
                .directory("/home/epilif3sotnas/NetBeansProjects/ASW/.env")
                .ignoreIfMalformed()
                .load();
        Algorithm algorithm = Algorithm.HMAC256(dotenv.get("JWT_SECRET"));
        String token = JWT.create()
                .withJWTId(ID)
                .withIssuer("auth0")
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }
}
