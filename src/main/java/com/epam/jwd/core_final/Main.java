package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.util.CrewReaderUtil;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.SpacemapReaderUtil;
import com.epam.jwd.core_final.util.SpaceshipsReaderUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            Application.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
       /* try {
            NassaContext.getInstance().init();
            Planet start = SpacemapServiceImpl.getInstance().getRandomPlanet();
            Planet end = SpacemapServiceImpl.getInstance().getRandomPlanet();
            FlightMission mission = MissionServiceImpl.getInstance().createMission("Ero", LocalDateTime.now(), start, end);
            System.out.println("Name: " + mission.getName());
            System.out.println("Start: " + mission.getStartDate().format(DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat())));
            System.out.println("End: " + mission.getEndDate().format(DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat())));
            System.out.println(mission.getStartPlanet().getName() + " - " + mission.getEndPlanet().getName());
            System.out.println("Distance: " + mission.getDistance());
            System.out.println("Spaceship: " + mission.getAssignedSpaceShip().getName() + " - " + mission.getAssignedSpaceShip().getFlightDistance());
        } catch (Exception ex) {
            System.out.println("ee");
            System.out.println(ex.getMessage());
        }*/

    }
}