package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ContaPoupanca extends Conta {

    private double rendimento;
}
