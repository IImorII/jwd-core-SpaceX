package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CrewReaderUtil {
    private static Logger logger = LoggerFactory.getLogger(CrewReaderUtil.class);

    public static void loadCrew(Collection<CrewMember> crewMembers) {
        CrewMemberFactory crewMemberFactory = null;
        String crewMask = null;
        String data = null;
        final String crewFileName = "src/main/resources/" +
                ApplicationProperties.getInputRootDir() + "/" +
                ApplicationProperties.getCrewFileName();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(crewFileName))) {
            crewMemberFactory = CrewMemberFactory.getInstance();
            crewMask = fileReader.readLine();
            data = fileReader.readLine();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        String[] crewData = data.split(";");
        crewMask = crewMask.substring(crewMask.indexOf("#") + 1, crewMask.indexOf("..."));
        String[] membersMasks = crewMask.split(";");
        int maskSize = membersMasks.length;
        for (int m = 0; m < crewData.length; m++) {
            String[] memberProps = membersMasks[m % maskSize].split(",");
            Role role = null;
            Rank rank = null;
            String name = null;
            String[] memberData = crewData[m].split(",");
            for (int j = 0; j < memberProps.length; j++) {
                switch (memberProps[j]) {
                    case "role":
                        role = Role.resolveRoleById(Integer.parseInt(memberData[j]));
                        break;
                    case "rank":
                        rank = Rank.resolveRankById(Integer.parseInt(memberData[j]));
                        break;
                    case "name":
                        name = memberData[j];
                        break;
                }
            }
            crewMembers.add(crewMemberFactory.create(name, role, rank));
        }
    }
}
