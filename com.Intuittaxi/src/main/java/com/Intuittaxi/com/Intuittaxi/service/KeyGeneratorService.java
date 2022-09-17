package com.Intuittaxi.com.Intuittaxi.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class KeyGeneratorService {
    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();

    public String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
