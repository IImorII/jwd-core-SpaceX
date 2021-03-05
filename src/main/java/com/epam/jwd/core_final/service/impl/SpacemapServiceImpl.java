package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SpacemapServiceImpl implements SpacemapService {

    private static SpacemapServiceImpl instance;

    public static SpacemapServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpacemapServiceImpl();
        }
        return instance;
    }

    private SpacemapServiceImpl() {

    }

    @Override
    public Planet getRandomPlanet() {
        List<Planet> planets = new ArrayList<>(NassaContext.getInstance().retrieveBaseEntityList(Planet.class));
        Random random = new Random();
        int ind = random.nextInt(planets.size() - 1);
        return planets.get(ind);
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return (int)Math.ceil(first.getLocation().distanceTo(second.getLocation()));
    }
}
