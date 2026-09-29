package org.example.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) {
                PROPERTIES.load(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static String get(String key) {
        return get(key, null);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }

    public static int getInt(String key, int defaultValue) {
        return Integer.parseInt(get(key, String.valueOf(defaultValue)));
    }
}
