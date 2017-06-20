package com.critc.plat;

import com.critc.plat.sys.listener.OnLineListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在线用户监听
 */
@Configuration
public class OnLineConfiguration {
    /**
     * 配置在线用户监听
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean onLineListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new OnLineListener());
        return servletListenerRegistrationBean;
    }
}