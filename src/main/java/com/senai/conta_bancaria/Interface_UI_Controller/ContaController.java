package com.senai.conta_bancaria.Interface_UI_Controller;

import com.senai.conta_bancaria.Application.DTO.ContaResumoDTO;
import com.senai.conta_bancaria.Application.Service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
