package com.foobarqix.camel.reproducers;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // comment out the following line to make the server start
        System.setProperty("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
        SpringApplication.run(HelloApplication.class, args);
    }

    @Bean
    TomcatServletWebServerFactory tomcat() {
        return new TomcatServletWebServerFactory() {

            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();

                return super.getTomcatWebServer(tomcat);
            }

        };
    }
}
