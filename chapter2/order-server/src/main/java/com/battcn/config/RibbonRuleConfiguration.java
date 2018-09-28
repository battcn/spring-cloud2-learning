package com.battcn.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 * @since 2018/9/28 0028
 */
@Configuration
public class RibbonRuleConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}