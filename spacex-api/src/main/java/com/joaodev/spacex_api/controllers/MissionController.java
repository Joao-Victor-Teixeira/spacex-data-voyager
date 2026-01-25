package com.joaodev.spacex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.services.MissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/missions")
@Tag(name = "Missions", description = "Controlador Rest das missões gerados pela API da SpaceX após processamento Batch")
public class MissionController {

    @Autowired
    private MissionService service;

     @Operation(
        summary = "Retorna todos as missões de forma paginada",
        description = "Retorna um JSON com todas as missões processados no batch, contendo os campos id, missionName, wikipedia e details ",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MissionDTO.class)))
            ),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public Page<MissionDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(
        summary = "Retorna uma missão por id",
        description = "Retorna um JSON com uma única missão correspondente ao id passado na requisição",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = MissionDTO.class))
            ),
            @ApiResponse(description = "Not Found - Missão não encontrada", responseCode = "404", content = @Content)
        })
    @GetMapping(value="/{id}", produces = "application/json")
    public MissionDTO findById(@PathVariable String id) {
        return service.findById(id);
    }
}
