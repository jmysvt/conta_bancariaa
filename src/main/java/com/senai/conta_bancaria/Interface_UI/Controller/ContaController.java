package com.senai.conta_bancaria.Interface_UI.Controller;

import com.senai.conta_bancaria.Application.DTO.*;
import com.senai.conta_bancaria.Application.Service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {

    //get de todas as contas e por numero de conta

    private final ContaService service;

    @GetMapping
    public ResponseEntity <List<ContaResumoDTO>> listarTodasAsContas(){
        return ResponseEntity.ok(service.listarTodasAsContas());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ContaResumoDTO> buscarNumeroAtiva(@PathVariable String numero){
        return ResponseEntity.ok(service.buscarNumeroAtiva(numero));
    }

    @PutMapping("/{numero}")
    public ResponseEntity<ContaResumoDTO> atualizarNumeroConta(@PathVariable String numero, @RequestBody ContaAtualizadaDTO dto){
        return ResponseEntity.ok(service.atualizarNumeroConta(numero, dto));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarConta(@PathVariable String numero){
        service.deletarConta(numero);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{numero}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar(@PathVariable String numero, @Valid @RequestBody ValorSaqueDepositoDTO dto){
        return ResponseEntity.ok(service.sacar(numero, dto));
    }

    @PostMapping("/{numero}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar(@PathVariable String numero, @Valid @RequestBody ValorSaqueDepositoDTO dto){
        return ResponseEntity.ok(service.depositar(numero, dto));
    }

    @PostMapping ("/{numero}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numero, @Valid @RequestBody TransferenciaDTO dto){
        return ResponseEntity.ok(service.transferir(numero, dto));
    }

    @PostMapping("/{numero}/rendimento")
    public ResponseEntity<ContaResumoDTO> aplicarRendimento(@PathVariable @Valid String numero){
        return ResponseEntity.ok(service.aplicarRendimento(numero));
    }

}
