package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "conta_numero", nullable = false)
    private Conta conta;

    private String boleto;

    private BigDecimal valorPago;

    private String dataPagamento;




}
