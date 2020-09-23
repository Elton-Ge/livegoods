package com.livegoods.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details
 * @version: 1.0
 */
@SpringBootApplication
@EnableCaching
public class DetailApplication {
    public static void main(String[] args) {
        SpringApplication.run(DetailApplication.class,args);
    }
}
