package com.joaodev.spacex_batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RocketDTO {
    
    private String id;
    
    private boolean active;
    private int stages;
    
    @JsonProperty("success_rate_pct")
    private int successRate;
    
    @JsonProperty("name")
    private String rocketName;
    
    private String description;

    public RocketDTO(){
    }

    public RocketDTO(String id, boolean active, int stages, int successRate, String rocketName, String description) {
        this.id = id;
        this.active = active;
        this.stages = stages;
        this.successRate = successRate;
        this.rocketName = rocketName;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStages() {
        return stages;
    }

    public void setStages(int stages) {
        this.stages = stages;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RocketDTO other = (RocketDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RocketDTO [id=" + id + ", active=" + active + ", stages=" + stages + ", successRate=" + successRate
                + ", rocketName=" + rocketName + ", description=" + description + "]";
    }
    
}
