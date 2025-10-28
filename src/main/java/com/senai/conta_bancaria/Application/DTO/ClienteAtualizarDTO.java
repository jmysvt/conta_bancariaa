package com.senai.conta_bancaria.Application.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ClienteAtualizarDTO(
        @NotBlank(message = "O campo nome não pode estar em branco")
        @Schema(description = "Nome para atualizar", example = "Bianca")
        String nome,

        @NotBlank(message = "O campo cpf não pode estar em branco")
        @Schema(description = "Cpf para atualizar", example = "12345678900")
        String cpf
){

}

