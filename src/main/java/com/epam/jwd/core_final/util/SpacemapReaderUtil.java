package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Planet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SpacemapReaderUtil {
    private static Logger logger = LoggerFactory.getLogger(SpacemapReaderUtil.class);
    public static void loadSpacemap(Collection<Planet> spacemap) {
        loadPlanetsData(spacemap);
    }

    private static void loadPlanetsData(Collection<Planet> spacemap) {
        final String spacemapFileName = "src/main/resources/" +
                ApplicationProperties.getInputRootDir() + "/" +
                ApplicationProperties.getPlanetsFileName();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(spacemapFileName))) {
            String line;
            int y = 0;
            while ((line = fileReader.readLine()) != null) {
                String[] lineArray = line.split(",");
                for (int x = 0; x < lineArray.length; x++) {
                    String name = lineArray[x];
                    if (!name.equals("null")) {
                        spacemap.add(new Planet(name,x + 1, y + 1));
                    }
                }
                y++;
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
