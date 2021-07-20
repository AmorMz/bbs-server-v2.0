package pers.muzi.bbs.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pers.muzi.bbs.interceptor.AuthInterceptor;
import pers.muzi.bbs.interceptor.CommitInterceptor;

/**
 * @author AmorMz
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    @Bean
    public CommitInterceptor getCommitInterceptor() {
        return new CommitInterceptor();
    }

    /**
     * 注册拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 用户认证
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .order(1);

        // 防止重复提交
        registry.addInterceptor(getCommitInterceptor())
                .addPathPatterns("/**")
                .order(2);
    }


}
