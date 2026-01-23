package com.joaodev.spacex_api.models.dto;

import org.springframework.hateoas.RepresentationModel;

import com.joaodev.spacex_api.models.entities.Launch;

import io.swagger.v3.oas.annotations.media.Schema;

public class LaunchDTO extends RepresentationModel<LaunchDTO> {

    @Schema(description = "ID único do lançamento", example = "697263cab5f990e3a82b2f4f")
    private String id;
    
    @Schema(description = "Número do lançamento", example = "1")
    private int flightNumber;
    
    @Schema(description = "Nome da missão", example = "FalconSat")
    private String missionName;
    
    @Schema(description = "Data do lançamento(Retorna uma String pois a API original serve em String)", example = "2006-03-24T22:30:00.000Z")
    private String launchDateUtc;
    
    @Schema(description = "Retorna o sucesso do lançamento (Retorna boolean)", example = "false")
    private boolean launchSuccess;
    
    @Schema(description = "Detalhes da missão", example = "Engine failure at 33 seconds and loss of vehicle")
    private String details;
    
    @Schema(description = "O nome do foguete que participou da missão", example = "falcon1")
    private String rocketId;

    public LaunchDTO(){
    }

    public LaunchDTO(String id, int flightNumber, String missionName, String launchDateUtc, boolean launchSuccess,
            String details, String rocketId) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launchDateUtc = launchDateUtc;
        this.launchSuccess = launchSuccess;
        this.details = details;
        this.rocketId = rocketId;
    }

    public LaunchDTO(Launch entity) {
        id = entity.getId();
        flightNumber = entity.getFlightNumber();
        missionName = entity.getMissionName();
        launchDateUtc = entity.getLaunchDateUtc();
        launchSuccess = entity.isLaunchSuccess();
        details = entity.getDetails();
        rocketId = entity.getRocketId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getLaunchDateUtc() {
        return launchDateUtc;
    }

    public void setLaunchDateUtc(String launchDateUtc) {
        this.launchDateUtc = launchDateUtc;
    }

    public boolean isLaunchSuccess() {
        return launchSuccess;
    }

    public void setLaunchSuccess(boolean launchSuccess) {
        this.launchSuccess = launchSuccess;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
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
        LaunchDTO other = (LaunchDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
