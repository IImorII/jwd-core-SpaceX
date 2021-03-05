package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    private static Logger logger = LoggerFactory.getLogger(CrewMemberFactory.class);

    private Constructor flightMissionConstructor = null;

    private static FlightMissionFactory instance;

    private FlightMissionFactory() {
        try {
            flightMissionConstructor = FlightMission.class.getDeclaredConstructor(String.class,
                    LocalDateTime.class, Planet.class, Planet.class);
        } catch (NoSuchMethodException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        try {
            if (args.length == flightMissionConstructor.getParameters().length) {
                flightMissionConstructor.setAccessible(true);
                return (FlightMission) flightMissionConstructor.newInstance((String) args[0], (LocalDateTime) args[1],
                        (Planet) args[2], (Planet) args[3]);
            } else {
                throw new InvalidStateException("FlightMissionFactory invalid num of args.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            flightMissionConstructor.setAccessible(false);
        }
        return null;
    }
}
