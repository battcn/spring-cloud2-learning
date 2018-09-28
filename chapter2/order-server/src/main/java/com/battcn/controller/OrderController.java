package com.battcn.controller;

import com.battcn.config.RibbonRuleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Levin
 * @since 2018/9/28 0028
 */
@RestController
@RequestMapping("/orders")
@RibbonClient(name = "ribbonRule",configuration = RibbonRuleConfiguration.class)
public class OrderController {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public OrderController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public String query() {
        final List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> list = discoveryClient.getInstances(service);
            for (ServiceInstance instance : list) {
                System.out.println(instance.getUri() + "/" + service + " - " + instance.getServiceId());
            }
        }
        return restTemplate.getForObject("http://PRODUCT-SERVER/products", String.class);
    }
}
