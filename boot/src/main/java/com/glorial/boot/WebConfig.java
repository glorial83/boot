package com.glorial.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web.xml관련 세팅
 *
 * @author glorial
 *
 * application.yml에 아래처럼 세팅해도 동일
	spring:
	  mvc:
	    view:
	      prefix: /WEB-INF/jsp/
	      suffix: .jsp
 *
 */
//@EnableWebMvc// --> 차라리 이걸 제거 하고 수동세팅하자
@Configuration
public class WebConfig implements WebMvcConfigurer /*WebMvcConfigurerAdapte는 5.x에서 사용하지 않음*/ {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }
}