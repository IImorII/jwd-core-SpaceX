package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NewMissionMenu implements ApplicationMenu {
    private String missionName = null;
    private Planet from = null;
    private Planet to = null;
    private int distance = 0;
    private int waitSeconds = 0;
    private Spaceship spaceship = null;

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("----------------");
        System.out.println("\nNew mission: ");
        System.out.println("Name: " + missionName);
        System.out.println("Path: " + from + " - " + to);
        System.out.println("Distance: " + distance);
        System.out.println("Assigned spaceship: " + spaceship);
        System.out.println("----------------");
        System.out.println("1. Set name.");
        System.out.println("2. Set planets.");
        System.out.println("3. Set start delay.");
        System.out.println("4. Set spaceship.");
        System.out.println("5. Create.");
        System.out.println("6. Cancel.");
        System.out.println("----------------");
    }

    @Override
    public void handleUserInput() {
        Scanner input = new Scanner(System.in);
        int key = input.nextInt();
        switch (key) {
            case 1:
                setName();
                break;
            case 2:
                setPlanets();
                break;
            case 3:
                setStartDelay();
                break;
            case 4:
                setSpaceship();
                break;
            case 5:
                createMission();
                break;
            case 6:
                back();
                break;
        }
    }

    private void back() {
        MenuHandler.getInstance().setActiveMenu(new MainMenu());
    }

    private void setName() {
        System.out.println("Input mission name: ");
        Scanner input = new Scanner(System.in);
        missionName = input.nextLine();
        System.out.println("\nName " + missionName + " set.");
    }

    private void setPlanets() {
        System.out.println("Available planets: ");
        Scanner input = new Scanner(System.in);
        int index = 1;
        List<Planet> planets = (List<Planet>) getApplicationContext().retrieveBaseEntityList(Planet.class);
        for (Planet planet : planets) {
            System.out.println((index++) + ": " + planet.toString());
        }
        System.out.println(index + ": Random planet");
        System.out.println("Choose start planet: ");
        index = input.nextInt();
        if (index == planets.size() + 1) {
            from = SpacemapServiceImpl.getInstance().getRandomPlanet();
        } else {
            from = planets.get(index - 1);
        }
        System.out.println("Choose end planet: ");
        index = input.nextInt();
        if (index == planets.size() + 1) {
            to = SpacemapServiceImpl.getInstance().getRandomPlanet();
        } else {
            to = planets.get(index - 1);
        }
        distance = SpacemapServiceImpl.getInstance().getDistanceBetweenPlanets(from, to);
    }

    private void setSpaceship() {
        if (from == null || to == null) {
            System.out.println("Set planets first!");
            return;
        }
        try {
            Criteria<Spaceship> criteria = new SpaceshipCriteria.SpaceshipCriteriaBuilder()
                    .setIsReadyForNextMissions(true)
                    .setFlightDistance((long) SpacemapServiceImpl.getInstance().getDistanceBetweenPlanets(from, to))
                    .build();
            System.out.println("Available spaceships: ");
            Scanner input = new Scanner(System.in);
            List<Spaceship> spaceships = SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(criteria);
            int index = 1;
            for (Spaceship spaceship : spaceships) {
                System.out.println((index++) + ": " + spaceship.toString());
            }
            System.out.println(index + ": Random spaceship");
            System.out.println("Choose spaceship: ");
            index = input.nextInt() - 1;
            if (index == spaceships.size()) {
                Random random = new Random();
                index = random.nextInt(spaceships.size());
                spaceship = spaceships.get(index);
            } else {
                spaceship = spaceships.get(index);
            }
        } catch (InvalidStateException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("\nSpaceship " + spaceship.getName() + " assigned to mission");
    }

    private void createMission() {
        if (spaceship == null || missionName == null) {
            System.out.println("Set name or assign spaceship to mission!");
            return;
        }
        try {
            LocalDateTime startTime = LocalDateTime.now().plusSeconds(waitSeconds);
            MissionServiceImpl.getInstance().createMissionWithSpaceship(spaceship, missionName, startTime, from, to);

        } catch (InvalidStateException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("\nMission " + missionName + " created!");
        back();
    }

    private void setStartDelay() {
        Scanner input = new Scanner(System.in);
        System.out.println("Set date: ");
        System.out.println("1. Set wait seconds after create: ");
        System.out.println("2. Instant start after create.");
        int key = input.nextInt();
        switch (key) {
            case 1:
                System.out.println("Input seconds: ");
                waitSeconds = input.nextInt();
                break;
            case 2:
                waitSeconds = 0;
                break;
        }
        System.out.println("\nDelay after create " + waitSeconds + " seconds set.");
    }
}
