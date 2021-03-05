package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    private static Logger logger = LoggerFactory.getLogger(CrewMemberFactory.class);

    private Constructor crewMemberConstructor = null;

    private static CrewMemberFactory instance;

    private CrewMemberFactory() {
        try {
            crewMemberConstructor = CrewMember.class.getDeclaredConstructor(String.class, Role.class, Rank.class);
        } catch (NoSuchMethodException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(Object... args) {
        try {
            if (args.length == crewMemberConstructor.getParameters().length) {
                crewMemberConstructor.setAccessible(true);
                return (CrewMember) crewMemberConstructor.newInstance((String) args[0], (Role) args[1], (Rank) args[2]);
            } else {
                throw new InvalidStateException("CrewMemberFactory invalid num of args.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            crewMemberConstructor.setAccessible(false);
        }
        return null;
    }
}
