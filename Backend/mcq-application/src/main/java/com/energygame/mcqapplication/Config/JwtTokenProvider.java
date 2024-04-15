package com.energygame.mcqapplication.Config;
import io.jsonwebtoken.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Base64;

@Component
public class JwtTokenProvider {

    private final String secretKey;

    // Constructor accepting secret key parameter
    public JwtTokenProvider(String secretKey) {
        this.secretKey = secretKey;
    }



// Method to generate JWT token
    public String generateToken(String userName) {
        try {
            return Jwts.builder()
                    .setSubject(userName)
                    // Add additional claims or information if needed
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
        } catch (Exception e) {
            return "Error generating token";
        }
    }

    // Method to decode the payload of a JWT token
    public String decodeToken(String token) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            System.out.println(payload);
            JSONObject jsonObject = new JSONObject(payload);
            return jsonObject.getString("sub");
        } catch (Exception e) {
            return "Error decoding token";
        }
    }

    // Method to validate a JWT token
    public ResponseEntity<?> validateToken(String token, String userName) {
        try {
            String generatedToken = "Bearer " + generateToken(userName);
            if (!token.equals(generatedToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            } else {
                return null;
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
