package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet startPlanet;
    private Planet endPlanet;

    private FlightMission(String name, LocalDateTime startDate, Planet startPlanet, Planet endPlanet) {
        this.name = name;
        this.missionResult = MissionResult.PLANNED;
        this.id = getId();
        this.startPlanet = startPlanet;
        this.endPlanet = endPlanet;
        this.distance = (long)SpacemapServiceImpl.getInstance().getDistanceBetweenPlanets(startPlanet, endPlanet);
        this.startDate = startDate;
        this.endDate = startDate.plusSeconds(distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getStartPlanet() {
        return startPlanet;
    }
    public Planet getEndPlanet() {
        return endPlanet;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nPath: " + startPlanet + " - " + endPlanet +
                "\nDistance: " + distance +
                "\nDate: " + startDate.format(DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat())) +
                " - " + endDate.format(DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat())) +
                "\nStatus: " + missionResult;
    }
}
