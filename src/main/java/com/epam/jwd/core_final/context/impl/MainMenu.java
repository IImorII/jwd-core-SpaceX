package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainMenu implements ApplicationMenu {

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("----------------");
        System.out.println("1. Create new mission.");
        System.out.println("2. Check current missions.");
        System.out.println("3. Check all spaceships.");
        System.out.println("4. Check all crew members.");
        System.out.println("5. Exit.");
        System.out.println("----------------");
    }

    @Override
    public void handleUserInput() {
        Scanner input = new Scanner(System.in);
        int key = input.nextInt();
        switch (key) {
            case 1: createNewMission(); break;
            case 2: checkCurrentMissions(); break;
            case 3: checkCurrentSpaceships(); break;
            case 4: checkCurrentCrewMembers(); break;
            case 5: exit(); break;
        }
    }


    public void createNewMission() {
        MenuHandler.getInstance().setActiveMenu(new NewMissionMenu());
    }

    public void exit() {
        System.out.println("Exit...");
        MenuHandler.getInstance().stop();
    }

    public void checkCurrentMissions() {
        System.out.println("\nMissions: ");
        List<FlightMission> missions = MissionServiceImpl.getInstance().findAllMissions();
        int index = 1;
        for (FlightMission f : missions) {
            System.out.println("\n" + (index++) + " ---------------- " + "\n" + f);
        }
    }

    private void checkCurrentSpaceships() {
        System.out.println("Spaceships: ");
        List<Spaceship> spaceships = SpaceshipServiceImpl.getInstance().findAllSpaceships();
        int index = 1;
        for (Spaceship s : spaceships) {
            System.out.println((index++) + ": " + s);
        }
    }

    private void checkCurrentCrewMembers() {
        System.out.println("Crew members: ");
        List<CrewMember> crew = CrewServiceImpl.getInstance().findAllCrewMembers();
        int index = 1;
        for (CrewMember c : crew) {
            System.out.println((index++) + ": " + c);
        }
    }
}
