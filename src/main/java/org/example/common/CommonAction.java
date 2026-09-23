package org.example.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

import static org.example.common.Config.IMPLICIT_WAIT;
import static org.example.common.Config.PLATFORM_AND_BROWSER;

public class CommonAction {

    private static WebDriver driver = null;

    private CommonAction() {
    }

    public static WebDriver createDriver() {

        if (driver == null) {
            switch (PLATFORM_AND_BROWSER) {
                case "win_chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(
                            "--headless=new",
                            "--window-size=1920,1080"
                    );
                    driver = new ChromeDriver(options);
                    break;
                default:
                    Assert.fail("Incorrect platform or browser name: " + PLATFORM_AND_BROWSER);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));

        }
        return driver;
    }
}
