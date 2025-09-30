package com.senai.conta_bancaria.Application.DTO;

import java.math.BigDecimal;

public record ContaAtualizadaDTO (
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal rendimento,
        BigDecimal taxa
){

}
