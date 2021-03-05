package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertyReaderUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */

    public static void loadProperties() {
        final String propertiesFileName = "src/main/resources/application.properties";
        try (FileInputStream stream = new FileInputStream(propertiesFileName)) {
            properties.load(stream);
            Field[] fields = ApplicationProperties.class.getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                if (f.getType().getSimpleName().equals("String")) {
                    f.set(null, properties.getProperty(f.getName()));
                }
                else if (f.getType().getSimpleName().equals("Integer")) {
                    f.set(null, Integer.parseInt(properties.getProperty(f.getName())));
                }
                else if (f.getType().getSimpleName().equals("Double")) {
                    f.set(null, Double.parseDouble(properties.getProperty(f.getName())));
                }
                f.setAccessible(false);
            }
        }
        catch (IOException | IllegalAccessException ex) {
            logger.error(ex.getMessage());
        }
    }
}
