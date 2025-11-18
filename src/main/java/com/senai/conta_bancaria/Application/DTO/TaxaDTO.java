package com.senai.conta_bancaria.Application.DTO;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record TaxaDTO(

        UUID id,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
        String descricao,

        @DecimalMin(value = "0.0", inclusive = false, message = "Percentual deve ser maior que zero")
        BigDecimal percentual,

        @DecimalMin(value = "0.0", inclusive = false, message = "Valor fixo deve ser maior que zero")
        BigDecimal valorFixo

) {

    // apenas um dos dois campos pode ser preenchido
    @AssertTrue(message = "Informe apenas percentual OU valorFixo (nunca os dois ou nenhum)")
    private boolean isTipoTaxaValido() {
        return (percentual != null && valorFixo == null) ||
                (percentual == null && valorFixo != null);
    }
}


