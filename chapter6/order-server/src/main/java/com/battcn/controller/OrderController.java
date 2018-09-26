package com.battcn.controller;

import com.battcn.api.ProductApi;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductApi productApi;


    @GetMapping
    public String query() {
        return this.productApi.selectProductById(10L);
    }


}
