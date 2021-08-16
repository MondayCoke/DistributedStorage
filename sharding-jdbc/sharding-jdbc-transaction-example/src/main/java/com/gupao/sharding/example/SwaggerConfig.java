package com.gupao.sharding.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis((input)->{
                    Class<?> declaringClass=input.declaringClass();
                    if(declaringClass.isAnnotationPresent(RestController.class)){
                        return true;
                    }
                    if(input.isAnnotatedWith(ResponseBody.class)){
                        return true;
                    }
                    return false;
                }).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Sharding-JDBC测试页面").version("1.0").build();
    }
}
