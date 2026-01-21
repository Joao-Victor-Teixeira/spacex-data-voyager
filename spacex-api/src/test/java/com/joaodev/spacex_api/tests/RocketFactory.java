package com.joaodev.spacex_api.tests;

import com.joaodev.spacex_api.models.dto.RocketDTO;
import com.joaodev.spacex_api.models.entities.Rocket;

public class RocketFactory {

    public static Rocket createRocket(){
        Rocket rocket = new Rocket("5e9d0d95eda69955f709d1eb", "Falcon 9", true, "abc");
        return rocket;
    }

    public static RocketDTO createRocketDTO(){
        Rocket rocket = createRocket();
        return new RocketDTO(rocket);
    }
}
