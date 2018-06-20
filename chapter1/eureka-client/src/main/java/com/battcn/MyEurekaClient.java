package com.battcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Levin
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MyEurekaClient {

    public static void main(String[] args) {
        SpringApplication.run(MyEurekaClient.class, args);
    }


}
