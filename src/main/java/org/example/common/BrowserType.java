package org.example.common;

import java.util.Arrays;

public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE;

    public static BrowserType fromString(String value) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Неподдерживаемый браузер: " + value));
    }
}
