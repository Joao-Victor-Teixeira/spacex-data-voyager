package com.joaodev.spacex_api.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rockets")
public class Rocket {

    @Id
    private String id;
    private String name;
    private boolean active;
    private String description;

    public Rocket(){
    }

    public Rocket(String id, String name, boolean active, String description) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.description = description;
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
