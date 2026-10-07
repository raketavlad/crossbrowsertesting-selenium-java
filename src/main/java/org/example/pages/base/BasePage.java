package org.example.pages.base;

import org.example.common.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Actions actions;
    private final String relativePath;

    public BasePage(WebDriver driver, String relativePath) {
        this.driver = driver;
        this.relativePath = relativePath;
        this.actions = new Actions(driver);
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

    // Можно доработать с учетом generics и отдавать уже экземпляр класса
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void inputText(By locator, String text) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.clear();
        field.sendKeys(text);
    }

    protected void hoverToElement(By locator) {
        actions.moveToElement(waitVisible(locator)).perform();
    }

    protected String getHexColor(By locator, String cssProperty) {
        return Color.fromString(driver.findElement(locator).getCssValue(cssProperty)).asHex();
    }
}
