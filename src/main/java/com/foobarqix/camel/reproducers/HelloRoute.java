package com.foobarqix.camel.reproducers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
class HelloRoute extends RouteBuilder {

    @Override
    public void configure() {
        rest("/hello")
          .get()
          // disable the request validation makes the route return the response
          //.clientRequestValidation(false)
          .produces(MediaType.APPLICATION_JSON_VALUE)
          .to("direct:hello");

        from("direct:hello")
          .setBody(constant("{\"message\":\"hello world\"}"))
          .setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE));
    }
}
