package com.energygame.mcqapplication.Config;

import com.energygame.mcqapplication.Model.User;
import io.jsonwebtoken.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.energygame.mcqapplication.Service.UserService;
import java.security.SignatureException;
import java.util.Base64;

@Component
public class JwtTokenProvider {

    // Define your secret key
    private static final String SECRET_KEY = "sgdsgfdhfghghhfhkawgkdsmjdbsnmcbwhjgyjweyuwqgdhawghdkgwhfdgwhkgdywafywgfhasgkassgdkjadgawhkafjfhagfkawfgk";




    // Method to generate JWT token
    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                // Add additional claims or information if needed
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String decodeToken(String token) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            System.out.println(payload);
            // Assuming the payload is in JSON format, parse it
            JSONObject jsonObject = new JSONObject(payload);
            String userName = jsonObject.getString("sub");
            return userName;
        } catch (Exception e) {
            return "Error";
        }
    }


    public Integer validateToken(String token, String userName) {
        try {

            String generatedToken = "Bearer "+generateToken(userName);

            // Compare tokens using equals method, not ==
            if (token.equals(generatedToken)) {
                return 200;
            } else {
                System.out.println(token);
                System.out.println(generatedToken);
                return 401;
            }
        } catch (Exception e) {
            return 401;
        }
    }



}

