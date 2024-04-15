package com.energygame.mcqapplication.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String secretKey() {
        // Define and return the secret key
        return "sgdsgfdhfghghhfhkawgkdsmjdbsnmcbwhjgyjweyuwqgdhawghdkgwhfdgwhkgdywafywgfhasgkassgdkjadgawhkafjfhagfkawfgk";
    }
}