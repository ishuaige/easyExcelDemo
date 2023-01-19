package com.niuma.easyexceldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 接口文档配置
 * <p>
 * https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 *
 * @author niumazlb
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("easyExcel-demo")
                        .description("easyExcel-demo")
                        .termsOfServiceUrl("Github")
                        .contact(new Contact("niuma", "Github", "邮箱"))
                        .version("1.0")
                        .build())
                .groupName("2.X版本")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.niuma.easyexceldemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}