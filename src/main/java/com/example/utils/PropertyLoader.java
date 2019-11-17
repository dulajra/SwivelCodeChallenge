package com.example.utils;

import com.example.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This can be used to load Java application properties from any valid properties file.
 *
 * @author Dulaj Atapattu
 */
public class PropertyLoader {

    public static Properties load(String propertyFileName) {
        Properties properties = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(propertyFileName)) {

            if (input == null) {
                System.err.println("Error while loading properties from " + propertyFileName);
                System.exit(1);
            }

            properties.load(input);

        } catch (IOException ex) {
            System.err.println("Error while loading properties from " + propertyFileName);
            ex.printStackTrace();
            System.exit(1);
        }

        return properties;
    }
}
