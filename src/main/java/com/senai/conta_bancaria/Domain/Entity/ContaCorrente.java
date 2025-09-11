package com.senai.conta_bancaria.Domain.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ContaCorrente extends Conta {

    private double limite;
    private double taxa;

}
