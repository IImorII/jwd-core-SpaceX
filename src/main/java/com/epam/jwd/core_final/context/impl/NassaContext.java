package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<FlightMission> missions = new ArrayList<>();

    private static NassaContext instance;

    private NassaContext() {

    }

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        switch (tClass.getSimpleName()) {
            case "CrewMember":
                return (Collection<T>) crewMembers;
            case "Spaceship":
                return (Collection<T>) spaceships;
            case "Planet":
                return (Collection<T>) planetMap;
            case "FlightMission":
                return (Collection<T>) missions;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */

    @Override
    public void init() throws InvalidStateException {
        PropertyReaderUtil.loadProperties();
        CrewReaderUtil.loadCrew(crewMembers);
        SpaceshipsReaderUtil.loadSpaceships(spaceships);
        SpacemapReaderUtil.loadSpacemap(planetMap);
        Timer timerUpdateMissions = new Timer(true);
        TimerTask updateMissions = UpdateUtil.getInstance();
        timerUpdateMissions.schedule(updateMissions, 10000, 10000);
        Timer timerUpdateFiles = new Timer(true);
        TimerTask updateFiles = UpdateFilesUtil.getInstance();
        timerUpdateFiles.schedule(updateFiles,20000, 60000);
    }
}
