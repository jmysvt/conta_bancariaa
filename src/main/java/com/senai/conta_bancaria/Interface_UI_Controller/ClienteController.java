package com.senai.conta_bancaria.Interface_UI_Controller;

import com.senai.conta_bancaria.Application.DTO.ClienteAtualizarDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Application.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity <ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto){
        ClienteResponseDTO novoCliente = service.registrarCliente(dto);

        return ResponseEntity.created(URI.create("/api/cliente/cpf/"+novoCliente.cpf())).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos(){
        return ResponseEntity.ok(service.listarClientesAtivos());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClientesCpfAtivo(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarClientesCpfAtivo(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteAtualizarDTO dto){
        return ResponseEntity.ok(service.atualizarCliente(cpf,dto));
    }


    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }




}
