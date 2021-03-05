package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class UpdateFilesUtil extends TimerTask {
    private final String crewFileName = "src/main/resources/" +
            ApplicationProperties.getInputRootDir() + "/" +
            ApplicationProperties.getCrewFileName();
    private final String spaceshipsFileName = "src/main/resources/" +
            ApplicationProperties.getInputRootDir() + "/" +
            ApplicationProperties.getSpaceshipsFileName();
    private final String spacemapFileName = "src/main/resources/" +
            ApplicationProperties.getInputRootDir() + "/" +
            ApplicationProperties.getPlanetsFileName();

    private final File crewFile = new File(crewFileName);;
    private final File spaceshipsFile = new File(spaceshipsFileName);
    private final File spacemapFile = new File(spaceshipsFileName);

    private long spacemapLastModified = spacemapFile.lastModified();
    private long spaceshipsLastModified = spaceshipsFile.lastModified();
    private long crewLastModified = crewFile.lastModified();

    private static UpdateFilesUtil instance;

    public static UpdateFilesUtil getInstance() {
        if (instance == null) {
            instance = new UpdateFilesUtil();
        }
        return instance;
    }

    private UpdateFilesUtil() {
    }

    @Override
    public void run() {
        update();
    }

    private void update() {
        if (crewFile.lastModified() != crewLastModified) {
            crewLastModified = crewFile.lastModified();
            CrewReaderUtil.loadCrew(NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class));
        }
        if (spaceshipsFile.lastModified() != spaceshipsLastModified) {
            spaceshipsLastModified = spaceshipsFile.lastModified();
            SpaceshipsReaderUtil.loadSpaceships(NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class));
        }
        if (spacemapFile.lastModified() != spacemapLastModified) {
            spacemapLastModified = spacemapFile.lastModified();
            SpacemapReaderUtil.loadSpacemap(NassaContext.getInstance().retrieveBaseEntityList(Planet.class));
        }
    }
}
