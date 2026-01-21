package com.joaodev.spacex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.services.RocketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/rockets")
@Tag(name = "Rockets", description = "Controlador Rest dos foguetes gerados pela API da SpaceX após processamento Batch")
public class RocketController {

    @Autowired
    private RocketService service;

     @Operation(
        summary = "Retorna todos os foguetes de forma paginada",
        description = "Retorna um JSON com todos os foguetes processados no batch, contendo os campos id, name, active, description .",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RocketDTO.class)))
            ),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public Page<RocketDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(
        summary = "Retorna um foguete por id",
        description = "Retorna um JSON com um único foguete correspondente ao id passado na requisição",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = RocketDTO.class))
            ),
            @ApiResponse(description = "Not Found - Usuário inexistente", responseCode = "404", content = @Content)
        })
    @GetMapping(value="/{id}", produces = "application/json")
    public RocketDTO findById(@PathVariable String id){
        return service.findById(id);
    }

    @Operation(
        summary = "Retorna uma pagina de foguetes ativos",
        description = "Retorna um JSON com todos os foguetes em atividade",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RocketDTO.class)))
            ),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
        })
    @GetMapping(value = "/active", produces = "application/json")
    public Page<RocketDTO> findAllActive(Pageable pageable,@RequestParam(value = "active", defaultValue = "true") Boolean active){
        return service.findAllActive(pageable, active);
    }
  
}
