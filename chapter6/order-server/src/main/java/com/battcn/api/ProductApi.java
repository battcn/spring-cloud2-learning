package com.battcn.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Levin
 * @since 2018/9/26 0026
 */
@FeignClient(name = "product-server/products", fallback = ProductApi.ProductApiFallback.class)
public interface ProductApi {

    /**
     * 根据产品ID查询产品信息
     *
     * @param productId ID
     * @return 查询结果
     */
    @GetMapping("{product_id}")
    String selectProductById(@PathVariable("product_id") Long productId);

    @Component
    class ProductApiFallback implements ProductApi {
        @Override
        public String selectProductById(Long productId) {
            return "feign error callback";
        }
    }

}
