/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

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
        return new JSONObject(verifier.verify(token).getPayload()).getString("ID");
    }
    
    public String createToken (String ID) {
        Dotenv dotenv = Dotenv.configure()
                .directory("/home/epilif3sotnas/NetBeansProjects/ASW/.env")
                .ignoreIfMalformed()
                .load();
        Algorithm alg = Algorithm.HMAC256(dotenv.get("JWT_SECRET"));
        Map<String, Object> payload = new HashMap<>();
        payload.put("ID", ID);
        payload.put("DateTime", LocalDateTime.now().toString());
        String token = JWT.create()
                .withPayload(payload)
                .sign(alg);
        return token;
    }
}
