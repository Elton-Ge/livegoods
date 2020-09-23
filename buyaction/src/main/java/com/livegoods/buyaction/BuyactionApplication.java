package com.livegoods.buyaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction
 * @version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.livegoods"})
public class BuyactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyactionApplication.class,args);
    }
}
