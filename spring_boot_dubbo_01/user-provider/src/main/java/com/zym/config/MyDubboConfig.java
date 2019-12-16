package com.zym.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangliang
 * @Date 2019/11/26 0026
 * @Description
 */

@Configuration
public class MyDubboConfig {

    @Bean
    public ApplicationConfig applicationConfig(){
        return new ApplicationConfig();
    }

    @Bean
    public ConsumerConfig consumerConfig(){
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(6000);
        consumerConfig.setCheck(false);
        return consumerConfig;
    }
}
