package br.com.treinow.repositories.jpa;

public interface EmailService {

    void sendWelcomeWithTemporaryPassword(String to, String name, String temporaryPassword);

    void sendCompleteRegistrationEmail(String to, String name, String setPasswordLink);
}
