package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class UpdateUtil extends TimerTask {
    private static UpdateUtil instance;

    public static UpdateUtil getInstance() {
        if (instance == null) {
            instance = new UpdateUtil();
        }
        return instance;
    }

    private UpdateUtil() {

    }

    @Override
    public void run() {
        update();
    }

    private void update() {
        List<FlightMission> activeMissions = new ArrayList<>(NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class))
                .stream()
                .filter(mission -> (mission.getMissionResult() == MissionResult.PLANNED || mission.getMissionResult() == MissionResult.IN_PROGRESS))
                .collect(Collectors.toList());
        for (FlightMission mission : activeMissions) {
            MissionServiceImpl.getInstance().updateMissionStatus(mission);
        }
    }
}
