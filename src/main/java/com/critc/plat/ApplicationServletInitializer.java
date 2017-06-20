package com.critc.plat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 以war包形式部署到web容器的启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.critc.plat")
public class ApplicationServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationServletInitializer.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationServletInitializer.class, args);
    }
}


