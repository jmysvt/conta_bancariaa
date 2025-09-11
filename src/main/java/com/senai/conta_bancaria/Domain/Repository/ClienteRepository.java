package com.senai.conta_bancaria.Domain.Repository;

import com.senai.conta_bancaria.Domain.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
