package com.battcn.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 * @since 2018/9/26 0026
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", "10086");
    }

}