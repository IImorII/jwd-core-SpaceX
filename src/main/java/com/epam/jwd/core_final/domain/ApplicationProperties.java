package com.epam.jwd.core_final.domain;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    //todo
    private static String inputRootDir = null;
    private static String outputRootDir = null;
    private static String crewFileName = null;
    private static String planetsFileName = null;
    private static String missionsFileName = null;
    private static String spaceshipsFileName = null;
    private static Integer fileRefreshRate = null;
    private static String dateTimeFormat = null;

    public static String getInputRootDir() {
        return inputRootDir;
    }

    public static String getOutputRootDir() {
        return outputRootDir;
    }

    public static String getCrewFileName() {
        return crewFileName;
    }

    public static String getPlanetsFileName() {
        return planetsFileName;
    }

    public static String getMissionsFileName() {
        return missionsFileName;
    }

    public static String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public static Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public static String getDateTimeFormat() {
        return dateTimeFormat;
    }


}
