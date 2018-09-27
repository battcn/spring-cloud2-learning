package com.battcn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/9/26 0026
 */
@RestController
@RequestMapping("/orders")
public class OrderController {


    @GetMapping
    public String query() {
        return "{orderId : 10086}";
    }


}
