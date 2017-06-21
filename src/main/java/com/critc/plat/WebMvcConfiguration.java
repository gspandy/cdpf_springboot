package com.critc.plat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringMvc 配置
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Value("${spring.mvc.view.prefix}")
    private String springMvcViewPrefix;
    @Value("${spring.mvc.view.suffix}")
    private String springMvcViewSuffix;
    @Value("${web.static.resource.server}")
    private String webStaticResourceServer;
    @Value("${web.dynamic.resource.server}")
    private String webDynamicResourceServer;
    @Value("${web.title}")
    private String webTitle;

    /**
     * 配置jsp解析器
     *
     * @return
     */
    @Bean
    public ViewResolver getDefaultViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver(springMvcViewPrefix, springMvcViewSuffix);
        resolver.getAttributesMap().put("staticServer", webStaticResourceServer);
        resolver.getAttributesMap().put("dynamicServer", webDynamicResourceServer);
        resolver.getAttributesMap().put("webTitle", webTitle);
        return resolver;
    }

    /**
     * json转换
     *
     * @return
     */
    @Bean
    public HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        return converter;
    }

    /**
     * TODO 设置支持上传文件
     *
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}