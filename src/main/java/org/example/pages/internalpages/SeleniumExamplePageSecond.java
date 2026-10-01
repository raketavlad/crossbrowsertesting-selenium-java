package org.example.pages.internalpages;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumExamplePageSecond extends BasePage {

    private final By headline = By.xpath("//h2");
    private final By content = By.xpath("//p");

    private static final String PATH = "/selenium_example_page2.html";

    public SeleniumExamplePageSecond(WebDriver driver) {
        super(driver, PATH);
    }

    public String getHeadline() {
        return driver.findElement(headline).getText();
    }

    public String getContent() {
        return driver.findElement(content).getText();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
