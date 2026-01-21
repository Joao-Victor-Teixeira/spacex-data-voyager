package com.joaodev.spacex_api.models.dto;

import com.joaodev.spacex_api.models.entities.Rocket;

public class RocketDTO {

    private String id;
    private String name;
    private boolean active;
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
