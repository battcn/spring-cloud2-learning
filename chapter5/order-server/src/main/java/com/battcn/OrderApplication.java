package com.battcn;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Autowired
    private ProductService productService;


    @GetMapping("/orders")
    public String query() {
        return productService.selectProducts();
    }

    @Service
    class ProductService {
        @HystrixCommand(fallbackMethod = "defaultProduct")
        public String selectProducts() {
            throw new RuntimeException("假设你在使用 LB 发送请求时报错了，或者对方服务 DOWN 机了");
        }

        public String defaultProduct() {
            return "error";
        }
    }
}
