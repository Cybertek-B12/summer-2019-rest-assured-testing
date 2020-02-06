package com.bookit.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            String path = "configuration.properties";

            FileInputStream stream = new FileInputStream(path);

            properties = new Properties();
            properties.load(stream);

            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) { return properties.getProperty(key); }
}
