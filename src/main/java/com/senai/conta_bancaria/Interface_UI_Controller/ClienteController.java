package com.senai.conta_bancaria.Interface_UI_Controller;

import com.senai.conta_bancaria.Application.DTO.ClienteRegistroDTO;
import com.senai.conta_bancaria.Application.DTO.ClienteResponseDTO;
import com.senai.conta_bancaria.Application.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto){
        return service.registrarCliente(dto);
    }

}
