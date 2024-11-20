package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendadorService {

    private final EmailService emailService;

    public AgendadorService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 8 * * *") 
    public void dispararEmailsDiarios() {
        emailService.enviarEmails();
    }
}
