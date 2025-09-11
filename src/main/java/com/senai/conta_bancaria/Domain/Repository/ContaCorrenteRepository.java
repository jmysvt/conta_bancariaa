package com.senai.conta_bancaria.Domain.Repository;

import com.senai.conta_bancaria.Domain.Entity.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, String> {
}
