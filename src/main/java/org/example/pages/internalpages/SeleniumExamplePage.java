package org.example.pages.internalpages;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
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
    private final By inputText = By.cssSelector("input[name='text']");
    private final By checkbox = By.cssSelector("input[name='checkbox']");
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
    private final By titleFormResults = By.cssSelector("#form-results p");
    private final By textResult = By.xpath("//span[text()='text']/following-sibling::span");
    private final By checkboxResult = By.xpath("//span[text()='checkbox']/following-sibling::span");
    private final By selectOptions = By.xpath("//span[text()='select']/following-sibling::span");
    private final By radioResult = By.xpath("//span[text()='radio']/following-sibling::span");
    private final By textareaResult = By.xpath("//span[text()='textarea']/following-sibling::span");

    private static final String PATH = "/selenium_example_page.html";

    public SeleniumExamplePage(WebDriver driver) {
        super(driver, PATH);
    }

    public String getHeadline() {
        return driver.findElement(headline).getText();
    }

    public String getIntro() {
        return driver.findElement(intro).getText();
    }

    public String getTitleList() {
        return driver.findElement(titleList).getText();
    }

    public List<String> getList() {
        return driver.findElements(list).stream()
                .map(WebElement::getText)
                .toList();
    }

    public SeleniumExamplePageSecond clickLink() {
        driver.findElement(link).click();
        return new SeleniumExamplePageSecond(driver);
    }

    public String checkButton() {
        driver.findElement(button).click();
        return driver.findElement(By.id("button-message")).getText();
    }

    public String checkTitleForm() {
        return driver.findElement(titleForm).getText();
    }

    public String checkInputTextPlaceholder() {
        return driver.findElement(inputText).getAttribute("placeholder");
    }

    public SeleniumExamplePage setText(String text) {
        driver.findElement(inputText).sendKeys(text);
        return this;
    }

    public String checkTextResult() {
        return driver.findElement(textResult).getText();
    }

    public SeleniumExamplePage chooseCheckbox() {
//        Видимо нужен дополнительный метод на проверку состояния чек-бокса
        driver.findElement(checkbox).click();
        return this;
    }

    public String getCheckbox() {
        String checkboxText = null;

        // По хорошему нужно переписать на нормальный wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            checkboxText = wait.until(d -> d.findElement(checkboxResult)).getText();
        } catch (TimeoutException ignored) {

        }
        return checkboxText;
    }

    public SeleniumExamplePage selectAnOption(String option) {
        driver.findElement(dropdown).click();
        // можно упростить через класс Select и Try-Catch (НУЖНО РАЗОБРАТЬСЯ И ОТРЕФАКТОРИТЬ)
        switch (option) {
            case ("option1"):
                driver.findElement(dropdownOption1).click();
                break;
            case ("option2"):
                driver.findElement(dropdownOption2).click();
                break;
            case ("option3"):
                driver.findElement(dropdownOption3).click();
                break;
            case ("option4"):
                driver.findElement(dropdownOption4).click();
                break;
            default:
                Assert.fail("Выбранная опция отсутствует в выпадающем списке");
                break;
        }
        return this;
    }

    public String checkSelectedOption() {
        return driver.findElement(selectOptions).getText();
    }

    public SeleniumExamplePage selectAnRadio(String radio) {
        // можно упростить через класс Select и Try-Catch (НУЖНО РАЗОБРАТЬСЯ И ОТРЕФАКТОРИТЬ)
        switch (radio) {
            case ("radio1"):
                driver.findElement(radio1).click();
                break;
            case ("radio2"):
                driver.findElement(radio2).click();
                break;
            default:
                Assert.fail("Выбранный radio отсутствует на странице");
                break;
        }
        return this;
    }

    public String checkSelectedRadio() {
        return driver.findElement(radioResult).getText();
    }

    public SeleniumExamplePage setTextarea(String text) {
        driver.findElement(textarea).sendKeys(text);
        return this;
    }

    public String checkTextarea() {
        return driver.findElement(textareaResult).getText();
    }

    public String checkResultTitle() {
        return driver.findElement(titleFormResults).getText();
    }

    public SeleniumExamplePage pressSubmitButton() {
        driver.findElement(submitButton).click();
        return this;
    }
}
