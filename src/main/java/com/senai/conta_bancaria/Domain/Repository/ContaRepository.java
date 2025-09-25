package com.senai.conta_bancaria.Domain.Repository;

import com.senai.conta_bancaria.Domain.Entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta,String> {
    List<Conta> findAllByAtivaTrue();

    Optional<Conta> findByNumeroAndAtivaTrue(String numero);
}

