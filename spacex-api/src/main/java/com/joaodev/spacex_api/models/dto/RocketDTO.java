package com.joaodev.spacex_api.models.dto;

import org.springframework.hateoas.RepresentationModel;

import com.joaodev.spacex_api.models.entities.Rocket;

import io.swagger.v3.oas.annotations.media.Schema;

public class RocketDTO extends RepresentationModel<RocketDTO>{

    @Schema(description = "ID único do foquete", example = "5e9d0d95eda69955f709d1eb")
    private String id;
    
    @Schema(description = "Nome do foguete", example = "Falcon 1")
    private String name;
    
    @Schema(description = "Estado de atividade do foguete(retornar boolean)", example = "false")
    private boolean active;
    
    @Schema(description = "Conteúdo sobre o fooguete", example = "The Falcon 1 was an expendable launch system privately...")
    private String description;

    public RocketDTO(){
    }

    public RocketDTO(String id, String name, boolean active, String description) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.description = description;
    }

    public RocketDTO(Rocket entity) {
        id = entity.getId();
        name = entity.getName();
        active = entity.isActive();
        description = entity.getDescription();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
