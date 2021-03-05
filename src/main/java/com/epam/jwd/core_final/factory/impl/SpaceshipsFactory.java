package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Map;

public class SpaceshipsFactory implements EntityFactory<Spaceship> {

    private static Logger logger = LoggerFactory.getLogger(CrewMemberFactory.class);

    private Constructor spaceshipConstructor = null;

    private static SpaceshipsFactory instance;

    private SpaceshipsFactory() {
        try {
            spaceshipConstructor = Spaceship.class.getDeclaredConstructor(String.class, Long.class, Map.class);
        } catch (NoSuchMethodException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static SpaceshipsFactory getInstance() {
        if (instance == null) {
            instance = new SpaceshipsFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {
        try {
            if (args.length == spaceshipConstructor.getParameters().length) {
                spaceshipConstructor.setAccessible(true);
                return (Spaceship) spaceshipConstructor.newInstance((String) args[0], (Long) args[1], (Map) args[2]);
            } else {
                throw new InvalidStateException("SpaceshipsFactory invalid num of args.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            spaceshipConstructor.setAccessible(false);
        }
        return null;
    }
}

