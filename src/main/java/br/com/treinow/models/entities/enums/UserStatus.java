package br.com.treinow.models.entities.enums;

public enum UserStatus {
    PENDING_SETUP, // Conta criada, aguardando o usuario definir a senha
    ACTIVE,       // Conta ativa e pronta para uso
    INACTIVE     // Conta inativa
}
