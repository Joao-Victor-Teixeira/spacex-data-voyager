package com.joaodev.spacex_launches_batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinksInnerDTO {

    @JsonProperty("mission_patch_small")
    private String missionPatchSmall;

    public LinksInnerDTO() {
    }

    public LinksInnerDTO(String missionPatchSmall) {
        this.missionPatchSmall = missionPatchSmall;
    }

    public String getMissionPatchSmall() {
        return missionPatchSmall;
    }

    public void setMissionPatchSmall(String missionPatchSmall) {
        this.missionPatchSmall = missionPatchSmall;
    }
}