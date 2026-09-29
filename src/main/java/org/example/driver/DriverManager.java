package org.example.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    /**
     * Каждый поток получает свою копию WebDriver.
     * Именно это обеспечивает thread-safety при параллельном запуске.
     */
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver не инициализирован для потока: " + Thread.currentThread().getName());
        }
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    /**
     * ВАЖНО: сначала quit(), затем remove() — иначе возможна утечка памяти.
     */
    public static void quitDriver() {
        if (hasDriver()) {
            try {
                DRIVER.get().quit();
            } finally {
                DRIVER.remove();
            }
        }
    }

}
