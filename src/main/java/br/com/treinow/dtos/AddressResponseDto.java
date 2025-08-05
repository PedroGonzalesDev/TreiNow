package br.com.treinow.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    UUID id;
    String street;
    Long number;
    String neighborhood;
    String city;
    String state;
}
