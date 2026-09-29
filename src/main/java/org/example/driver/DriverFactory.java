package org.example.driver;

import org.example.common.BrowserType;
import org.example.common.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browserName) {
        BrowserType browser = BrowserType.fromString(browserName);
        boolean headless = ConfigReader.getBoolean("headless", false);
        WebDriver driver = switch (browser) {
            case CHROME -> new ChromeDriver(buildChromeOptions(headless));
            case FIREFOX -> new FirefoxDriver(buildFirefoxOptions(headless));
            case EDGE -> new EdgeDriver(buildEdgeOptions(headless));
        };

        configureTimeouts(driver);
        driver.manage().window().maximize();
        return driver;
    }

    private static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        if (headless) {
            options.addArguments(
                    "--headless=new",
                    "--window-size=1920,1080");
        }
        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return options;
    }

    private static EdgeOptions buildEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static void configureTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getInt("implicit.wait", 0)));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigReader.getInt("page.load.timeout", 30)));
    }
}