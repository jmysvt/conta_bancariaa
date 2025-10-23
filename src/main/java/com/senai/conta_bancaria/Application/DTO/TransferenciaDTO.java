package com.senai.conta_bancaria.Application.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotBlank(message = "A conta de destino é obrigatória")
        @Schema(description = "Conta destino para a transferencia", example = "567-8")
        String contaDestino,

        @Positive(message = "O valor da transferência deve ser positivo")
        @Schema(description = "Valor para a transferência (positivo)", example = "100")
        BigDecimal valor
) {
}
