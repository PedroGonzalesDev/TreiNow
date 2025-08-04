package br.com.treinow.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    UUID id;
    UUID userId;
    Long membershipId;

}

