package org.example.pages.internalpages;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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
    private final By buttonText = By.id("button-message");

    // Селекторы формы ввода
    private final By titleForm = By.xpath("//p[text()='Form Elements']");
    private final By inputText = By.cssSelector("input[name='text']");
    private final By checkbox = By.cssSelector("input[name='checkbox']");
    private final By dropdown = By.id("dropdown");
    private final By textarea = By.xpath("//textarea");
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headline)).getText();
    }

    public String getIntro() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(intro)).getText();
    }

    public String getTitleLinkAndButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleLinkAndButton)).getText();
    }

    public String getTitleList() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleList)).getText();
    }

    public List<String> getList() {
        return driver.findElements(list).stream()
                .map(WebElement::getText)
                .toList();
    }

    public SeleniumExamplePageSecond clickLink() {
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        return new SeleniumExamplePageSecond(driver);
    }

    public SeleniumExamplePage clickButton() {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        return this;
    }

    public String getButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonText)).getText();
    }

    public String getTitleForm() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleForm)).getText();
    }

    public String getInputTextPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputText)).getAttribute("placeholder");
    }

    public SeleniumExamplePage setText(String text) {
        driver.findElement(inputText).sendKeys(text);
        return this;
    }

    public String getTextResult() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textResult)).getText();
    }

    public SeleniumExamplePage selectCheckbox(boolean checked) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (element.isSelected() != checked) {
            element.click();
        }
        return this;
    }

    public String getCheckboxResult(boolean isSelect) {
        if (isSelect) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxResult))
                    .getText();
        } else {
            return "";
        }
    }

    public SeleniumExamplePage selectAnOption(String option) {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByValue(option);
        return this;
    }

    public String getSelectedOption() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectOptions)).getText();
    }

    public SeleniumExamplePage selectAnRadio(String radio) {
        By locator = By.cssSelector(String.format("input[value='%s']", radio));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return this;
    }

    public String getSelectedRadio() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(radioResult)).getText();
    }

    public SeleniumExamplePage setTextarea(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(textarea)).sendKeys(text);
        return this;
    }

    public String getTextarea() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(textareaResult)).getText();
    }

    public String getResultTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleFormResults)).getText();
    }

    public SeleniumExamplePage pressSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }
}