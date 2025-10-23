package com.senai.conta_bancaria.Application.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ValorSaqueDepositoDTO(
        @NotNull(message = "O valor é obrigatório")
        @Schema(description = "Valor para saque e Deposito", example = "300")
        BigDecimal valor
) {

}
