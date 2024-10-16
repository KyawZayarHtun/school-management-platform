package com.mail.service.mail;

import com.mail.service.mail.dto.MailReq;
import com.mail.service.mail.dto.MailDetail;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record MailService(
        MailRepo mailRepo
) {

    Mono<MailDetail> insertMail(Mono<MailReq> mailReqMono) {
        return mailReqMono.map(Mail::new)
                .flatMap(mailRepo::insert)
                .map(MailDetail::new);
    }

    Flux<MailDetail> getAllMail() {
        return mailRepo.findAll().map(MailDetail::new);
    }

}
