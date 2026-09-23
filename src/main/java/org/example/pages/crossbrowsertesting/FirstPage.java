package org.example.pages.crossbrowsertesting;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class FirstPage extends BasePage {

    private final By headline = By.xpath("(//h2)[1]");
    private final By intro = By.id("intro");
    private final By titleList = By.xpath("(//h2)[1]/following-sibling::p[2]");
    private final By list = By.cssSelector(".list > li");
    private final By link = By.cssSelector("div#link-test a");
    private final By button = By.cssSelector("div#link-test button");

    private final By input = By.cssSelector("#myform input[type='text']");
    private final By checkbox = By.cssSelector("#myform input[type='checkbox']");

    private final By dropdown = By.cssSelector("#myform #dropdown");
    private final By dropdownOption1 = By.cssSelector("#myform #dropdown [value='option1']");
    private final By dropdownOption2 = By.cssSelector("#myform #dropdown [value='option2']");
    private final By dropdownOption3 = By.cssSelector("#myform #dropdown [value='option3']");
    private final By dropdownOption4 = By.cssSelector("#myform #dropdown [value='option4']");


    private final By textarea = By.cssSelector("#myform textarea");
    private final By radio1 = By.cssSelector("#myform [value='radio1']");
    private final By radio2 = By.cssSelector("#myform [value='radio2']");

    private final By submitButton = By.cssSelector("#myform #submitbtn");

    public FirstPage(WebDriver driver) {
        super(driver);
    }

    public FirstPage checkHeadline() {
        Assert.assertEquals(driver.findElement(headline).getText(), "Selenium Test Example Page");
        return this;
    }

    public FirstPage checkIntro() {
        Assert.assertEquals(driver.findElement(intro).getText(), "A very basic example page for" +
                " running remote Selenium Tests on the CrossBrowserTesting.com platform.");
        return this;
    }

    public FirstPage checkTitleList() {
        Assert.assertEquals(driver.findElement(titleList).getText(), "Unordered List");
        return this;
    }

    public FirstPage checkList() {
        List<String> actualList = driver.findElements(list).stream()
                .map(WebElement::getText)
                .toList();
        Assert.assertEquals(actualList, List.of("One", "Two", "Three", "Four"));
        return this;
    }

    public FirstPage checkLink() {
        driver.findElement(link).click();
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://crossbrowsertesting.github.io/selenium_example_page2.html");
        Assert.assertEquals(driver.findElement(By.cssSelector("div h2")).getText(),
                "Selenium Example Page 2");
        Assert.assertEquals(driver.findElement(By.cssSelector("div p")).getText(),
                "I am content on page 2!");
        return this;
    }

    public FirstPage checkButton() {
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(By.id("button-message")).getText(),
                "I am the message!!");
        return this;
    }
}
