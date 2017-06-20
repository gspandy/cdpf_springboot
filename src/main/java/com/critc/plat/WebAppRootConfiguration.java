package com.critc.plat;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.WebAppRootListener;

/**
 * WebAppRoot配置
 */
@Configuration
public class WebAppRootConfiguration {

    /**
     * 配置Web应用的名称
     * <p>
     * 对应application.properties:
     * server.context-parameters.webAppRootKey=cdpf.root
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean webAppRootListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new WebAppRootListener());
        return servletListenerRegistrationBean;
    }
}