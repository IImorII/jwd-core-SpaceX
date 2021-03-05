package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private static Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);
    private static MissionServiceImpl instance;

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    private MissionServiceImpl() {

    }

    public void saveAllMissionsToFile() {
        String path = "src/main/resources/output/missions.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            for (FlightMission m : NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)) {
                writer.write(m.toString());
                writer.write("\n----------------");
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
    public void releaseAssignedSpaceships(FlightMission mission) {
        mission.getAssignedSpaceShip().setIsReadyForNextMissions(true);
    }

    public void releaseAssignedCrew(FlightMission mission) {
        for (CrewMember c : mission.getAssignedCrew()) {
            c.setIsReadyForNextMissions(true);
        }
    }

    public void updateMissionStatus(FlightMission mission) {
        if (mission.getMissionResult() == MissionResult.PLANNED) {
            if (mission.getStartDate().isBefore(LocalDateTime.now())) {
                mission.setMissionResult(MissionResult.IN_PROGRESS);
            }
        }
        if (mission.getMissionResult() == MissionResult.IN_PROGRESS) {
            if (mission.getEndDate().isBefore(LocalDateTime.now())) {
                mission.setMissionResult(MissionResult.COMPLETED);
                releaseAssignedCrew(mission);
                releaseAssignedSpaceships(mission);
            } else {
                Random random = new Random();
                int destroyedParts = random.nextInt(100);
                // Chance 5% to be destroyed
                if (destroyedParts > 95) {
                    mission.setMissionResult(MissionResult.FAILED);
                }
            }
        }
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        List<FlightMission> list = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (flightMissionCriteria.getName() == null) {
                        return true;
                    } else return flightMission.getName().equals(flightMissionCriteria.getName());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getDistance() == null) {
                        return true;
                    } else return flightMission.getDistance().equals(flightMissionCriteria.getDistance());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getStartDate() == null) {
                        return true;
                    } else return flightMission.getStartDate().equals(flightMissionCriteria.getStartDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getEndDate() == null) {
                        return true;
                    } else return flightMission.getEndDate().equals(flightMissionCriteria.getEndDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getMissionResult() == null) {
                        return true;
                    } else return flightMission.getMissionResult().equals(flightMissionCriteria.getMissionResult());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getId() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NullPointerException("Mission not found.");
        } else {
            return list;
        }
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        Optional<FlightMission> optional = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (flightMissionCriteria.getName() == null) {
                        return true;
                    } else return flightMission.getName().equals(flightMissionCriteria.getName());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getDistance() == null) {
                        return true;
                    } else return flightMission.getDistance().equals(flightMissionCriteria.getDistance());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getStartDate() == null) {
                        return true;
                    } else return flightMission.getStartDate().equals(flightMissionCriteria.getStartDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getEndDate() == null) {
                        return true;
                    } else return flightMission.getEndDate().equals(flightMissionCriteria.getEndDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getMissionResult() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getId() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).findAny();

        if (optional.isEmpty()) {
            throw new NullPointerException("Mission not found.");
        } else {
            return optional;
        }
    }


    @Override
    public FlightMission createMission(Object... args) throws InvalidStateException {
        FlightMission newFlightMission = FlightMissionFactory.getInstance().create(args);
        newFlightMission.setAssignedSpaceShip(setMissionSpaceship(newFlightMission));
        newFlightMission.setAssignedCrew(setMissionCrew(newFlightMission.getAssignedSpaceShip()));
        NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class).add(newFlightMission);
        return newFlightMission;
    }

    public FlightMission createMissionWithSpaceship(Spaceship spaceship, Object... args) throws InvalidStateException {
        FlightMission newFlightMission = FlightMissionFactory.getInstance().create(args);
        newFlightMission.setAssignedSpaceShip(spaceship);
        spaceship.setIsReadyForNextMissions(false);
        newFlightMission.setAssignedCrew(setMissionCrew(newFlightMission.getAssignedSpaceShip()));
        NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class).add(newFlightMission);
        return newFlightMission;
    }

    //Auto search
    private Spaceship setMissionSpaceship(FlightMission flightMission) throws InvalidStateException {
        Optional<Spaceship> missionSpaceship = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class).stream()
                .filter(spaceship -> spaceship.getFlightDistance() >= flightMission.getDistance())
                .filter(Spaceship::getIsReadyForNextMissions)
                .findAny();
        if (missionSpaceship.isPresent()) {
            missionSpaceship.get().setIsReadyForNextMissions(false);
            return missionSpaceship.get();
        } else {
            throw new InvalidStateException("No available spaceships for that mission.");
        }
    }

    private List<CrewMember> setMissionCrew(Spaceship spaceship) throws InvalidStateException {
        int needCount = 0;
        for (Short s : spaceship.getCrew().values()) {
            needCount += s;
        }
        List<CrewMember> missionCrew = new ArrayList<>();
        List<Role> needCrew = new ArrayList<>(spaceship.getCrew().keySet());
        for (Role role : needCrew) {
            List<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                    .stream()
                    .filter(crewMember -> crewMember.getRole() == role)
                    .filter(CrewMember::getIsReadyForNextMissions)
                    .peek(crewMember -> crewMember.setIsReadyForNextMissions(false))
                    .limit(spaceship.getCrew().get(role))
                    .collect(Collectors.toList());
            missionCrew.addAll(crewMembers);
        }
        if (missionCrew.size() < needCount) {
            throw new InvalidStateException("No available crew for that spaceship.");
        }
        return missionCrew;
    }

}
