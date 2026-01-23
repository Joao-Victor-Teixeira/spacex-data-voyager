package com.joaodev.spacex_api.tests;

import com.joaodev.spacex_api.models.dto.LaunchDTO;
import com.joaodev.spacex_api.models.entities.Launch;

public class LaunchFactory {

    public static Launch createLaunch() {
        Launch launch = new Launch("697263cab5f990e3a82b2f4f", 1, "FalconSat",
                "2006-03-24T22:30:00.000Z", false, "Engine failure at 33 seconds and loss of vehicle", "falcon1");

        return launch;
    }

    public static LaunchDTO createLaunchDTO() {
        Launch launch = createLaunch();
        return new LaunchDTO(launch);
    }
}
