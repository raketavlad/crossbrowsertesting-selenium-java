package org.example.pages.internalpages;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SeleniumExamplePage extends BasePage {

    // Селекторы шапки
    private final By headline = By.xpath("//h2[contains(text(), 'Selenium')]");
    private final By intro = By.id("intro");

    // Селекторы блока со списком
    private final By titleList = By.xpath("//p[contains(text(), 'List')]");
    private final By list = By.cssSelector(".list > li");

    // Селекторы блока с ссылкой и кнопкой
    private final By titleLinkAndButton = By.xpath("//p[text()='Links and buttons']");
    private final By link = By.cssSelector("div#link-test a");
    private final By button = By.cssSelector("div#link-test button");

    // Селекторы формы ввода
    private final By titleForm = By.xpath("//p[text()='Form Elements']");
    private final By input = By.cssSelector("input[type='text']");
    private final By checkbox = By.cssSelector("input[type='checkbox']");
    private final By dropdown = By.id("dropdown");
    private final By dropdownOption1 = By.cssSelector("option[value='option1']");
    private final By dropdownOption2 = By.cssSelector("option[value='option2']");
    private final By dropdownOption3 = By.cssSelector("option[value='option3']");
    private final By dropdownOption4 = By.cssSelector("option[value='option4']");
    private final By textarea = By.xpath("//textarea");
    private final By radio1 = By.id("radiobtn1");
    private final By radio2 = By.cssSelector("input[value='radio2']");
    private final By submitButton = By.cssSelector("#submitbtn");


    // Селекторы результата
    private final By formResults = By.cssSelector("#form-results p");
    private final By textResult = By.xpath("//span[text()='text']/following-sibling::span");
    private final By checkboxResult = By.xpath("//span[text()='checkbox']/following-sibling::span");
    private final By selectOptions = By.xpath("//span[text()='select']/following-sibling::span");
    private final By radioResult = By.xpath("//span[text()='radio']/following-sibling::span");
    private final By getTextareaResult = By.xpath("//span[text()='textarea']/following-sibling::span");


    public SeleniumExamplePage(WebDriver driver) {
        super(driver);
    }

    public SeleniumExamplePage checkHeadline() {
        Assert.assertEquals(driver.findElement(headline).getText(), "Selenium Test Example Page");
        return this;
    }

    public SeleniumExamplePage checkIntro() {
        Assert.assertEquals(driver.findElement(intro).getText(), "A very basic example page for" +
                " running remote Selenium Tests on the CrossBrowserTesting.com platform.");
        return this;
    }

    public SeleniumExamplePage checkTitleList() {
        Assert.assertEquals(driver.findElement(titleList).getText(), "Unordered List");
        return this;
    }

    public SeleniumExamplePage checkList() {
        List<String> actualList = driver.findElements(list).stream()
                .map(WebElement::getText)
                .toList();
        Assert.assertEquals(actualList, List.of("One", "Two", "Three", "Four"));
        return this;
    }

    public SeleniumExamplePage checkLink() {
        driver.findElement(link).click();
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://crossbrowsertesting.github.io/selenium_example_page2.html");
        Assert.assertEquals(driver.findElement(By.cssSelector("div h2")).getText(),
                "Selenium Example Page 2");
        Assert.assertEquals(driver.findElement(By.cssSelector("div p")).getText(),
                "I am content on page 2!");
        return this;
    }

    public SeleniumExamplePage checkButton() {
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(By.id("button-message")).getText(),
                "I am the message!!");
        return this;
    }
}
