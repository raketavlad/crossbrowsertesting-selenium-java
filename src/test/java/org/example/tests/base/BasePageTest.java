package org.example.tests.base;

import org.example.driver.DriverFactory;
import org.example.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BasePageTest {

    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        // 1. Создаём драйвер под конкретный браузер
        driver = DriverFactory.createDriver(browser);

        // 2. Сохраняем в ThreadLocal (изоляция между потоками)
        DriverManager.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // alwaysRun=true гарантирует закрытие даже если @BeforeMethod упал
        DriverManager.quitDriver();
        this.driver = null;
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
