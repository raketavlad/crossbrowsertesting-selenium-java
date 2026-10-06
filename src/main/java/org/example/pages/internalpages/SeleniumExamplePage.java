package org.example.pages.internalpages;

import io.qameta.allure.Step;
import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SeleniumExamplePage extends BasePage {

    private static final String PATH = "/selenium_example_page.html";

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

    public SeleniumExamplePage(WebDriver driver) {
        super(driver, PATH);
    }


    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    public String getIntro() {
        return waitVisible(intro).getText();
    }

    public String getTitleLinkAndButton() {
        return waitVisible(titleLinkAndButton).getText();
    }

    @Step("Получить заголовок блока List")
    public String getTitleList() {
        return waitVisible(titleList).getText();
    }

    @Step("Получить значения списка")
    public List<String> getList() {
        return driver.findElements(list).stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Нажать на ссылку перехода на вторую страницу")
    public SeleniumExamplePageSecond clickLink() {
        click(link);
        return new SeleniumExamplePageSecond(driver);
    }

    @Step("Нажать на кнопку Show Message")
    public SeleniumExamplePage clickButton() {
        click(button);
        return this;
    }

    @Step("Получить текст, который появился после нажатия кнопки")
    public String getButtonText() {
        return waitVisible(buttonText).getText();
    }

    @Step("Получить заголовок формы")
    public String getTitleForm() {
        return waitVisible(titleForm).getText();
    }

    @Step("Получить placeholder поля ввода")
    public String getInputTextPlaceholder() {
        return waitVisible(inputText).getAttribute("placeholder");
    }

    @Step("Заполнить поле ввода текстом")
    public SeleniumExamplePage setText(String text) {
        inputText(inputText, text);
        return this;
    }

    @Step("Получить текст из результата")
    public String getTextResult() {
        return waitVisible(textResult).getText();
    }

    @Step("Нажать на чек-бокс")
    public SeleniumExamplePage selectCheckbox(boolean checked) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (element.isSelected() != checked) {
            element.click();
        }
        return this;
    }

    @Step("Получить значение чек-бокса из результата")
    public String getCheckboxResult(boolean isSelect) {
        if (isSelect) {
            return waitVisible(checkboxResult).getText();
        } else {
            return "";
        }
    }

    @Step("Выбрать опцию из выпадающего списка")
    public SeleniumExamplePage selectAnOption(String option) {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByValue(option);
        return this;
    }

    @Step("Получить значение выбранной опции из результата")
    public String getSelectedOption() {
        return waitVisible(selectOptions).getText();
    }

    @Step("Нажать на одно значение radio")
    public SeleniumExamplePage selectAnRadio(String radio) {
        By locator = By.cssSelector(String.format("input[value='%s']", radio));
        click(locator);
        return this;
    }

    @Step("Получить значение выбранного radio из результата")
    public String getSelectedRadio() {
        return waitVisible(radioResult).getText();
    }

    @Step("Заполнить текстом блок textarea")
    public SeleniumExamplePage setTextarea(String text) {
        inputText(textarea, text);
        return this;
    }

    @Step("Получить значение textarea из результата")
    public String getTextarea() {
        return waitVisible(textareaResult).getText();
    }

    @Step("Получить значение заголовка формы результата")
    public String getResultTitle() {
        return waitVisible(titleFormResults).getText();
    }

    @Step("Нажать кнопку Submit")
    public SeleniumExamplePage pressSubmitButton() {
        click(submitButton);
        return this;
    }
}