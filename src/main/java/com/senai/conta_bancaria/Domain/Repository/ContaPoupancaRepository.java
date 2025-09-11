package com.senai.conta_bancaria.Domain.Repository;

import com.senai.conta_bancaria.Domain.Entity.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca,String> {
}
