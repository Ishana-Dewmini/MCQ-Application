package com.energygame.mcqapplication.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProviderFactory {

    private final String secretKey;

    @Autowired
    public JwtTokenProviderFactory(String secretKey) {
        this.secretKey = secretKey;
    }


}
