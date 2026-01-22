package com.joaodev.spacex_launches_batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RocketInnerDTO {

    @JsonProperty("rocket_id")
    private String rocketId;

    public RocketInnerDTO() {
    }

    public RocketInnerDTO(String rocketId) {
        this.rocketId = rocketId;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
    }
}