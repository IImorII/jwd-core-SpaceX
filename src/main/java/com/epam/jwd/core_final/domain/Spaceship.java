package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions = true;

    private Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        this.name = name;
        this.flightDistance = flightDistance;
        this.crew = crew;
        this.id = getId();
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getIsReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setIsReadyForNextMissions(Boolean value) {
        this.isReadyForNextMissions = value;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Distance: " + flightDistance + " | Need crew: " + crew + " | Is available: " + isReadyForNextMissions;
    }
}
