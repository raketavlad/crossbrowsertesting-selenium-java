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
    private final By formResults = By.cssSelector("#form-results p");
    private final By textResult = By.xpath("//span[text()='text']/following-sibling::span");
    private final By checkboxResult = By.xpath("//span[text()='checkbox']/following-sibling::span");
    private final By selectOptions = By.xpath("//span[text()='select']/following-sibling::span");
    private final By radioResult = By.xpath("//span[text()='radio']/following-sibling::span");
    private final By textareaResult = By.xpath("//span[text()='textarea']/following-sibling::span");


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

    public SeleniumExamplePage checkTitleForm() {
        Assert.assertEquals(driver.findElement(titleForm).getText(), "Form Elements");
        return this;
    }

    public SeleniumExamplePage checkInputTextPlaceholder() {
        Assert.assertEquals(driver.findElement(inputText).getAttribute("placeholder"),
                "Input Text Here");
        return this;
    }

    public SeleniumExamplePage setText(String text) {
        driver.findElement(inputText).sendKeys(text);
        return this;
    }

    public SeleniumExamplePage checkTextResult(String text) {
        Assert.assertEquals(driver.findElement(textResult).getText(),
                text);
        return this;
    }

    public SeleniumExamplePage chooseCheckbox(boolean isOn) {
        if (isOn) {
            driver.findElement(checkbox).click();
        }
        return this;
    }

    public SeleniumExamplePage checkCheckbox(boolean expectedExist) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = null;
        try {
            element = wait.until(d -> d.findElement(checkboxResult));
        } catch (TimeoutException ignored) {

        }

        if (expectedExist) {
            Assert.assertNotNull(element, "Ошибка: Ожидали, что элемент появится, но его нет на странице!");
            Assert.assertEquals(element.getText(), "on", "Текст элемента не соответствует 'on'!");
        } else {
            Assert.assertNull(element, "Ошибка: Ожидали, что элемента НЕ БУДЕТ, но он присутствует на странице!");
        }
        return this;
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

    public SeleniumExamplePage checkSelectedOption(String option) {
        Assert.assertEquals(driver.findElement(selectOptions).getText(), option,
                "Опция не соответствует выбранной");
        return this;
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

    public SeleniumExamplePage checkSelectedRadio(String radio) {
        Assert.assertEquals(driver.findElement(radioResult).getText(), radio,
                "Radio не соответствует выбранному");
        return this;
    }

    public SeleniumExamplePage setTextarea(String text) {
        driver.findElement(textarea).sendKeys(text);
        return this;
    }

    public SeleniumExamplePage checkTextarea(String text) {
        Assert.assertEquals(driver.findElement(textareaResult).getText(), text);
        return this;
    }

    public SeleniumExamplePage pressSubmitButton() {
        driver.findElement(submitButton).click();
        return this;
    }


}
