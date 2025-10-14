package com.senai.conta_bancaria.Application.DTO;


import jakarta.validation.constraints.NotBlank;

public record ClienteAtualizarDTO(
        @NotBlank(message = "O campo nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O campo cpf não pode estar em branco")
        String cpf
){

}

