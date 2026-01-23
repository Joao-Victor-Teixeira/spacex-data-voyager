package com.joaodev.spacex_missions_batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joaodev.spacex_missions_batch.domain.Mission;

public class MissionDTO {

    private String id;
    @JsonProperty("mission_name")
    private String missionName;
    private String wikipedia;
    private String description;

    public MissionDTO(){
    }

    public MissionDTO(String id, String missionName, String wikipedia, String description) {
        this.id = id;
        this.missionName = missionName;
        this.wikipedia = wikipedia;
        this.description = description;
    }
    
    public MissionDTO(Mission entity) {
        id = entity.getId();
        missionName = entity.getMissionName();
        wikipedia = entity.getWikipedia();
        description = entity.getDescription();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
