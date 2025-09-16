package com.senai.conta_bancaria.Application.Service;

import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Domain.Repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteRegistroDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(ClienteRegistroDTO::fromEntity)
                .toList();
    }


}
