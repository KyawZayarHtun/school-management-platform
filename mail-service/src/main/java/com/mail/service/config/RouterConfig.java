package com.mail.service.config;

import com.mail.service.mail.MailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(MailHandler mailHandler) {
        RequestPredicate acceptType = accept(APPLICATION_JSON);
        return nest(path("/mails"), route()
                .GET("", acceptType, req -> mailHandler.getAllMailDetail())
                .POST("", acceptType, mailHandler::sendMail)
                .build());
    }

}
