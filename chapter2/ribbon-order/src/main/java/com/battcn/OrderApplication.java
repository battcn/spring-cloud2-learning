package com.battcn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Levin
 */
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public OrderApplication(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }


    @GetMapping("/orders")
    public String query() {
        final List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> list = discoveryClient.getInstances(service);
            for (ServiceInstance aList : list) {
                System.out.println(aList.getUri() + "/" + service + " - " + aList.getServiceId());
            }
        }
        return restTemplate.getForObject("http://RIBBON-PRODUCT/products/1", String.class);
    }

    @Configuration
    class MyConfiguration {
        @LoadBalanced
        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

}
