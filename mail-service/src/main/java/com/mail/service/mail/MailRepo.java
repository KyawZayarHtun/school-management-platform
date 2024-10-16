package com.mail.service.mail;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface MailRepo extends ReactiveMongoRepository<Mail, String> {
}
