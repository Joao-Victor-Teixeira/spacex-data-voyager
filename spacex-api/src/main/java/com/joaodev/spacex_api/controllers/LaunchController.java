package com.joaodev.spacex_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.spacex_api.controllers.assembler.LaunchModelAssembler;
import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;
import com.joaodev.spacex_api.services.LaunchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/launches")
@Tag(name = "Launches", description = "Controlador Rest dos lançamentos gerados pela API da SpaceX após processamento Batch")
public class LaunchController {

    @Autowired
    private LaunchService service;

    @Autowired
    private LaunchModelAssembler assembler;

    @Operation(
        summary = "Retorna todos os lançamentos de forma paginada",
        description = "Retorna um JSON com todos os lançamentos processados no batch, contendo os campos id, flightNumber,"
                    +"missionName, launchDateUtc, launchSuccess, details e rocketId" ,
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LaunchDTO.class)))
            ),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<PagedModel<LaunchDTO>> findAll(
        Pageable pageable,
        PagedResourcesAssembler<Launch> pagedResourcesAssembler){

            Page<Launch> page = service.findAll(pageable);
            PagedModel<LaunchDTO> pagedModel = pagedResourcesAssembler.toModel(page, assembler);

            return ResponseEntity.ok(pagedModel);
    }
    

    @Operation(
        summary = "Retorna um lançamento por id",
        description = "Retorna um JSON com um único lançamento correspondente ao id passado na requisição",
        responses = {
            @ApiResponse(
                description = "Sucesso", 
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = LaunchDTO.class))
            ),
            @ApiResponse(description = "Not Found - Lançamento não encontrado", responseCode = "404", content = @Content)
        })
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<LaunchDTO> findById(@PathVariable String id) {
        Launch launch = service.findById(id);
        LaunchDTO dto = assembler.toModel(launch);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Retorna uma pagina de lançamentos que obtiveram sucesso", description = "Retorna um JSON com todos os lançamentos de sucesso", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LaunchDTO.class)))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
    })
    @GetMapping(value = "/success", produces = "application/json")
    public ResponseEntity<PagedModel<LaunchDTO>> findByLaunchSuccess(Pageable pageable,
            @RequestParam(defaultValue = "true") Boolean launchSuccess,
            PagedResourcesAssembler<Launch> pagedResourcesAssembler ) {
        
                Page<Launch> page = service.findByLaunchSuccess(launchSuccess, pageable);
                PagedModel<LaunchDTO> pagedModel = pagedResourcesAssembler.toModel(page, assembler);
               
                return ResponseEntity.ok(pagedModel);
    }
}
