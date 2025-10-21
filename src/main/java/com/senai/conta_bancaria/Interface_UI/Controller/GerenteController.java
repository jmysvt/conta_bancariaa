package com.senai.conta_bancaria.Interface_UI.Controller;

import com.senai.conta_bancaria.Application.DTO.GerenteDTO;
import com.senai.conta_bancaria.Application.Service.GerenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
@RequiredArgsConstructor
public class GerenteController {

    private final GerenteService service;

    @GetMapping
    public ResponseEntity<List<GerenteDTO>> listarTodosGerentes() {
        List<GerenteDTO> gerentes = service.listarTodosGerentes();
        return ResponseEntity.ok(gerentes);
    }

    @PostMapping
    public ResponseEntity<GerenteDTO> cadastrarGerente(@RequestBody GerenteDTO dto) {
        GerenteDTO gerenteCriado = service.cadastrarGerente(dto);
        return ResponseEntity.ok(gerenteCriado);
    }


}
