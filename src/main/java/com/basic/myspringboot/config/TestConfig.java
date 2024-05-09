package com.basic.myspringboot.config;

import com.basic.myspringboot.config.vo.CustomerVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Bean
    public CustomerVO customerVO() {
        return CustomerVO.builder() //CustomerVOBuilder
                .id(200L)
                .mode("개발환경")
                .build();
    }
}
