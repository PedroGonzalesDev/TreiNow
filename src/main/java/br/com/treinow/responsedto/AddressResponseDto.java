package br.com.treinow.responsedto;

import java.util.UUID;


public record AddressResponseDto (
    UUID id,
    String street,
    Long number,
    String neighborhood,
    String city,
    String state
){}
