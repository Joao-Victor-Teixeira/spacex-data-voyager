package com.joaodev.spacex_api.tests;

import com.joaodev.spacex_api.models.dto.MissionDTO;
import com.joaodev.spacex_api.models.entities.Mission;

public class MissionFactory {

    public static Mission createMission(){
        Mission mission = new Mission("6973fcae89d8f1ef9014f1c7", "Thaicom", "https://en.wikipedia.org/wiki/Thaicom", "Thaicom is the name of a series of communications ....");
        return mission;
    }

    public static MissionDTO createMissionDTO(){
        Mission mission = createMission();
        return new MissionDTO(mission);
    }
}
