package com.ericsson.iot.rest;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:camel-rest-context.xml"})
@ComponentScan("com.ericsson.iot.rest.beans")
public class Application extends SpringBootServletInitializer{
	
    /**
     * main method to start this application
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    /*@Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(
            new CamelHttpTransportServlet(), "/v1/api/*");
        servlet.setName("CamelServlet");
        return servlet;
    }*/

}
