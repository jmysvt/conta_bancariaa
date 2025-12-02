package com.senai.conta_bancaria.Interface_UI.Controller;

import com.senai.conta_bancaria.Application.DTO.TaxaDTO;
import com.senai.conta_bancaria.Application.Service.TaxaService;
import com.senai.conta_bancaria.Domain.Entity.Taxa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/taxas")
@RequiredArgsConstructor
public class TaxaController {
    private final TaxaService taxaService;
    @PostMapping
    public ResponseEntity<Taxa> criar(@RequestBody TaxaDTO dto) {
        return ResponseEntity.ok(taxaService.create(dto));
    }
    @GetMapping
    public ResponseEntity<List<Taxa>> findAll() {
        return ResponseEntity.ok(taxaService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Taxa> findById(@PathVariable String id) {
        return ResponseEntity.ok(taxaService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        taxaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


