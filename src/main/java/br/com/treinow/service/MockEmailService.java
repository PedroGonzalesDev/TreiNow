package br.com.treinow.service;


import br.com.treinow.repositories.jpa.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service // Marca esta classe como um serviço do Spring
public class MockEmailService implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendWelcomeWithTemporaryPassword(String to, String name, String temporaryPassword) {
        LOG.info("--- INÍCIO DO E-MAIL (MOCK) ---");
        LOG.info("PARA: " + to);
        LOG.info("ASSUNTO: Boas-vindas à TreinoW!");
        LOG.info("CORPO:");
        LOG.info("Olá, " + name + "!");
        LOG.info("Sua conta foi criada com sucesso por um de nossos funcionários.");
        LOG.info("Sua senha temporária é: " + temporaryPassword);
        LOG.info("Recomendamos que você a altere no seu primeiro acesso.");
        LOG.info("--- FIM DO E-MAIL (MOCK) ---");
    }

    @Override
    public void sendCompleteRegistrationEmail(String to, String name, String setPasswordLink) {
        LOG.info("--- INÍCIO DO E-MAIL (MOCK) ---");
        LOG.info("PARA: " + to);
        LOG.info("ASSUNTO: Finalize seu cadastro na TreinoW!");
        LOG.info("CORPO:");
        LOG.info("Olá, " + name + "!");
        LOG.info("Estamos quase lá! Para finalizar seu cadastro e ativar sua conta, por favor, clique no link abaixo para definir sua senha:");
        LOG.info(setPasswordLink);
        LOG.info("Este link é válido por 24 horas.");
        LOG.info("--- FIM DO E-MAIL (MOCK) ---");
    }
}