package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SpaceshipsReaderUtil {
    private static Logger logger = LoggerFactory.getLogger(SpaceshipsReaderUtil.class);

    public static void loadSpaceships(Collection<Spaceship> spaceships) {
        List<String> data = new ArrayList<>();
        List<String> mask = new ArrayList<>();
        loadSpaceshipsData(data, mask);
        parseSpaceshipsData(data, mask, spaceships);
    }

    private static void loadSpaceshipsData(List<String> data, List<String> mask) {
        final String spaceshipsFileName = "src/main/resources/" +
                ApplicationProperties.getInputRootDir() + "/" +
                ApplicationProperties.getSpaceshipsFileName();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(spaceshipsFileName))) {
            String s;
            while ((s = fileReader.readLine()) != null) {
                if (s.startsWith("#")) {
                    if (!s.endsWith(".")) {
                        mask.add(s.replaceFirst("#", ""));
                    }
                } else {
                    data.add(s);
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    private static void parseSpaceshipsData(List<String> data, List<String> mask, Collection<Spaceship> spaceships) {
        SpaceshipsFactory spaceshipsFactory = SpaceshipsFactory.getInstance();
        for (int i = 0; i < data.size(); i++) {
            String[] propsMask = mask.get(i % mask.size()).split(";");
            String[] propsData = data.get(i).split(";");
            String name = null;
            Long distance = null;
            Map<Role, Short> crew = new HashMap<>();
            for (int j = 0; j < propsMask.length; j++) {
                String m = propsMask[j];
                String d = propsData[j];
                if (m.equals("name")) {
                    name = d;
                } else if (m.equals("distance")) {
                    distance = Long.parseLong(d);
                } else if (m.startsWith("crew")) {
                    parseCrewData(d, m, crew);
                }
            }
            spaceships.add(spaceshipsFactory.create(name, distance, crew));
        }
    }

    private static void parseCrewData(String data, String mask, Map<Role, Short> crew) {
        String[] propsMask = mask.substring(mask.indexOf("{") + 1, mask.indexOf("}")).split(",");
        String[] propsData = data.substring(data.indexOf("{") + 1, data.indexOf("}")).split(",");
        Role key = null;
        Short value = null;
        for (int i = 0; i < propsMask.length; i++) {
            String[] m = propsMask[i].split(":");
            String[] d = propsData[i].split(":");
            if (m[0].equals("roleid")) {
                key = Role.resolveRoleById(Integer.parseInt(d[0]));
                value = Short.parseShort(d[1]);
            } else if (m[0].equals("count")) {
                key = Role.resolveRoleById(Integer.parseInt(d[1]));
                value = Short.parseShort(d[0]);
            } else {
                logger.error("Invalid mask format!");
                throw new RuntimeException("Invalid mask format!");
            }
            crew.put(key, value);
        }
    }
}
