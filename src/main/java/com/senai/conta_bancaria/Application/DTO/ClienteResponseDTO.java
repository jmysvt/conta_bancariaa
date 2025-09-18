package com.senai.conta_bancaria.Application.DTO;

import java.util.List;

public record ClienteResponseDTO (
    String nome,
    String cpf,
    String id,

    List<ContaResumoDTO> contas
){

}
