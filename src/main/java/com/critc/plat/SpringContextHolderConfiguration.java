package com.critc.plat;

import com.critc.plat.core.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringContextConfiguration
 */
@Configuration
public class SpringContextHolderConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 配置springContextConfiguration
     *
     * @return
     */
    @Bean
    public SpringContextHolder springContextConfiguration() {
        return new SpringContextHolder();
    }
}