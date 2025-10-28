package com.senai.conta_bancaria.Interface_UI.Controller;

import com.senai.conta_bancaria.Application.DTO.ServicoDTO;
import com.senai.conta_bancaria.Application.Service.ServicoAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Serviços", description = "Gerenciamento de serviços do banco")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoAppService service;

    public ServicoController(ServicoAppService service) {
        this.service = service;
    }

    @Operation(
            summary = "Criar uma nova conta",
            description = "Cria uma nova conta bancária para um cliente, com saldo inicial e tipo de conta definidos.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ServicoDTO.class),
                            examples = @ExampleObject(name = "Exemplo válido", value = """
                                        {
                                          "numeroConta": "12345-6",
                                          "tipoConta": "CORRENTE",
                                          "saldo": 500.0,
                                          "clienteId": "1a2b3c"
                                        }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Saldo inválido", value = "\"Saldo inicial deve ser maior ou igual a 0\""),
                                            @ExampleObject(name = "Tipo inválido", value = "\"Tipo de conta não reconhecido\"")
                                    }
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ServicoDTO> criar(@Valid @RequestBody ServicoDTO dto) {
        return ResponseEntity
                .status(201)
                .body(service.salvar(dto));
    }

    @Operation(
            summary = "Listar todas as contas",
            description = "Retorna todas as contas bancárias cadastradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listar() {
        return ResponseEntity
                .ok(service.listar());
    }

    @Operation(
            summary = "Buscar conta por ID",
            description = "Retorna uma conta bancária a partir do seu identificador único.",
            parameters = {
                    @Parameter(name = "id", description = "ID da conta a ser buscada", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conta encontrada"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Conta não encontrada.\"")
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity
                .ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar conta",
            description = "Atualiza informações de uma conta bancária existente (ex: tipo de conta ou saldo).",
            parameters = {
                    @Parameter(name = "id", description = "ID da conta a ser atualizada", example = "1")
            },
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ServicoDTO.class),
                            examples = @ExampleObject(name = "Exemplo de atualização", value = """
                        {
                          "numeroConta": "12345-6",
                          "tipoConta": "POUPANCA",
                          "saldo": 1200.0,
                          "clienteId": "1a2b3c"
                        }
                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "Saldo inválido", value = "\"Saldo deve ser maior ou igual a 0\""),
                                            @ExampleObject(name = "Tipo inválido", value = "\"Tipo de conta não reconhecido\"")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Conta com ID 99 não encontrada.\"")
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable String id, @Valid @RequestBody ServicoDTO dto) {
        return ResponseEntity
                .ok(service.atualizar(id, dto));
    }

    @Operation(
            summary = "Deletar uma conta",
            description = "Remove uma conta bancária da base de dados a partir do seu ID",
            parameters = {
                    @Parameter(name = "id", description = "ID da conta a ser deletada", example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Conta removida com sucesso"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Conta não encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "\"Conta com ID 99 não encontrada.\"")
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
