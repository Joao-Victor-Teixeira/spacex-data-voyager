package com.joaodev.spacex_launches_batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "launches")
public class Launch {

    @Id
    private String id;
    private int flightNumber;
    private String missionName;
    private String launchDateUtc;
    private boolean launchSuccess;
    private String details;
    
    private String rocketId;

    public Launch(){
    }

    public Launch(String id, int flightNumber, String missionName, String launchDateUtc, boolean launchSuccess,
            String details, String rocketId) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launchDateUtc = launchDateUtc;
        this.launchSuccess = launchSuccess;
        this.details = details;
        this.rocketId = rocketId;
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
        Launch other = (Launch) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Launch [id=" + id + ", flightNumber=" + flightNumber + ", missionName=" + missionName
                + ", launchDateUtc=" + launchDateUtc + ", launchSuccess=" + launchSuccess + ", details=" + details
                + ", rocketId=" + rocketId + "]";
    }

  

}
