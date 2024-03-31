package com.energygame.mcqapplication.Config;

import io.jsonwebtoken.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

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

    public boolean validateToken(String token) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            System.out.println(payload);
            // Assuming the payload is in JSON format, parse it
            JSONObject jsonObject = new JSONObject(payload);
            String userName = jsonObject.getString("sub");

            String generatedToken = generateToken(userName);

            // Compare tokens using equals method, not ==
            if (token.equals(generatedToken)) {
                return true;
            } else {
                System.out.println(token);
                System.out.println(generatedToken);
                return false;
            }
        } catch (Exception e) {
            System.out.println("token: "+token);
            System.out.println(e);
            return false; // Token is invalid
        }
    }



}

