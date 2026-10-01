package org.example.pages.base;

import org.example.common.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final String relativePath;

    public BasePage(WebDriver driver, String relativePath) {
        this.driver = driver;
        this.relativePath = relativePath;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getInt("explicit.wait", 15)));
    }

    public void open() {
        String baseUrl = ConfigReader.get("base.url", "https://crossbrowsertesting.github.io");
        driver.get(baseUrl + relativePath);
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
