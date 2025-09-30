package com.senai.conta_bancaria.Application.DTO;

import java.math.BigDecimal;

public record TransferenciaDTO(
        String contaDestino,
        BigDecimal valor
) {
}
