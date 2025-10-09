package com.senai.conta_bancaria.Application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotBlank(message = "A conta de destino é obrigatória")
        String contaDestino,

        @Positive(message = "O valor da transferência deve ser positivo")
        BigDecimal valor
) {
}
