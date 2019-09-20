package com.dmz.dmzServer.config;

import com.dmz.dmzServer.Utils.ApiSignInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    /*
    * @Configuration ，config形式加载在容器中,
    * 其中addPathPatterns 和 excludePathPatterns 方法，
    * 从方法名就可以看出来，是针对拦截器的范围控制，
    * 上面的代码就是针对从互联网访问交易网/outToInner/** 生效，
    * 对/innerToOut 不需要签名认证，进行拦截
    * */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiSignInterceptor()).addPathPatterns("/outToInner/**").excludePathPatterns("/innerToOut/**");
        super.addInterceptors(registry);
    }
}