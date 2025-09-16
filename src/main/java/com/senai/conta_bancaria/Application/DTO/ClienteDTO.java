package com.senai.conta_bancaria.Application.DTO;

import com.senai.conta_bancaria.Domain.Entity.Cliente;
import com.senai.conta_bancaria.Domain.Entity.Conta;

import java.util.ArrayList;
import java.util.List;

public record ClienteDTO (
        String nome,
        String cpf,
        List<Conta> contas
){

    public static ClienteDTO fromEntity (Cliente cliente){
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() !=null ? cliente.getContas(): List.of()
        );
    }

    public Cliente toEntity (){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setContas(this.contas !=null? new ArrayList<>(this.contas): new ArrayList<>());
        return cliente;
    };
        }
