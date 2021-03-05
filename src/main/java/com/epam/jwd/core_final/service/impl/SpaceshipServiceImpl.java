package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.SpaceshipsFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    private SpaceshipServiceImpl() {

    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) throws InvalidStateException {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        List<Spaceship> list = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (spaceshipCriteria.getCrew() == null) {
                        return true;
                    } else return spaceship.getCrew() == spaceshipCriteria.getCrew();
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getFlightDistance() == null) {
                        return true;
                    } else return spaceship.getFlightDistance() >= spaceshipCriteria.getFlightDistance();
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getName() == null) {
                        return true;
                    } else return spaceship.getName().equals(spaceshipCriteria.getName());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getId() == null) {
                        return true;
                    } else return spaceship.getId().equals(spaceshipCriteria.getId());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getIsReadyForNextMissions() == null) {
                        return true;
                    } else
                        return spaceship.getIsReadyForNextMissions() == spaceshipCriteria.getIsReadyForNextMissions();
                }).collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new InvalidStateException("Spaceships not found.");
        } else {
            return list;
        }
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (spaceshipCriteria.getCrew() == null) {
                        return true;
                    } else return spaceship.getCrew() == spaceshipCriteria.getCrew();
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getFlightDistance() == null) {
                        return true;
                    } else return spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getName() == null) {
                        return true;
                    } else return spaceship.getName().equals(spaceshipCriteria.getName());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getId() == null) {
                        return true;
                    } else return spaceship.getId().equals(spaceshipCriteria.getId());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getIsReadyForNextMissions() == null) {
                        return true;
                    } else return spaceship.getIsReadyForNextMissions() == spaceshipCriteria.getIsReadyForNextMissions();
                }).findAny();

        if (optional.isEmpty()) {
            throw new NullPointerException("Spaceship not found.");
        } else {
            return optional;
        }
    }

    @Override
    public Spaceship createSpaceship(Object ... args) throws RuntimeException {
        Spaceship newSpaceship = SpaceshipsFactory.getInstance().create(args);
        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceship.equals(newSpaceship))
                .findAny();

        if (optional.isPresent()) {
            throw new RuntimeException("Spaceship exist.");
        } else {
            return newSpaceship;
        }
    }
}
