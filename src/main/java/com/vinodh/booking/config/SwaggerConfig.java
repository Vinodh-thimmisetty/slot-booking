package com.vinodh.booking.config;

import com.vinodh.booking.entity.Section;
import com.vinodh.booking.entity.Slot;
import com.vinodh.booking.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @author Vinodh Kumar T
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TRADE_MARK = "\u2122";
    private static final String NAME = "VINODH";

    public static final Contact DEFAULT_CONTACT = new Contact(
            NAME + TRADE_MARK,
            "https://www.vinodh.com",
            "vinodh5052@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            NAME + TRADE_MARK,
            NAME + TRADE_MARK,
            "v1",
            "vinodh related terms" + TRADE_MARK,
            DEFAULT_CONTACT,
            "License details",
            "http://www.vinodh.com/licenses",
            Collections.emptyList());

    @Bean
    public Docket slotBookingAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.vinodh.booking.controller"))
                .paths(PathSelectors.ant("/v1/*/**"))
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .ignoredParameterTypes(Section.class, Slot.class, User.class);
    }
}