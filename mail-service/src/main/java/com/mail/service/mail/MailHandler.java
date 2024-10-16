package com.mail.service.mail;

import com.mail.service.error.ValidationErr;
import com.mail.service.mail.dto.MailDetail;
import com.mail.service.mail.dto.MailReq;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record MailHandler(
        MailService mailService,
        MailClient mailClient,
        Validator validator
) {


    public Mono<ServerResponse> sendMail(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MailReq.class)
                .flatMap(req -> {
                    var errors = new BeanPropertyBindingResult(req, MailReq.class.getName());
                    validator.validate(req, errors);

                    if (errors.hasErrors()) {
                        return Mono.error(() -> new ValidationErr(errors));
                    }

                    return mailClient.sendSimpleMail(req).then(ServerResponse.ok().bodyValue("Thanks"));
                });
    }

    public Mono<ServerResponse> getAllMailDetail() {
        return ServerResponse.ok().body(mailService.getAllMail(), MailDetail.class);
    }


}
