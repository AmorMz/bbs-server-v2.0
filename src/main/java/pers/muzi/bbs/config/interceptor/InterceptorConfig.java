package pers.muzi.bbs.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pers.muzi.bbs.interceptor.AuthInterceptor;

/**
 * @author AmorMz
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    /**
     * 注册拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**");
    }


}
