package org.example.pages.internalpages;

import io.qameta.allure.Step;
import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TodoPage extends BasePage {

    private static final String PATH = "/todo-app.html";
    private static final int REMAINING_COUNT_INDEX = 0;
    private static final int ALL_COUNT_INDEX = 2;

    private final By headline = By.cssSelector("h2");
    private final By todoCounts = By.cssSelector("span.ng-binding");

    private final By archiveButton = By.xpath("//a[text()='archive']");
    private final By todoInput = By.cssSelector("input#todotext");
    private final By addButton = By.id("addbutton");


    public TodoPage(WebDriver driver) {
        super(driver, PATH);
    }

    @Step("Получить заголовок страницы")
    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    @Step("Получить общее количество todo на странице")
    public int getAllTodoCount() {
        return getCountTodo(ALL_COUNT_INDEX);
    }

    @Step("Получить количество незавершенных todo на странице")
    public int getRemainingTodoCount() {
        return getCountTodo(REMAINING_COUNT_INDEX);
    }

    private int getCountTodo(int index) {
        String[] array = waitVisible(todoCounts).getText().split(" ");
        return Integer.parseInt(array[index]);
    }

    @Step("Получить текст todo")
    public String getTodoText(String todoText) {
        return waitVisible(getTodoLocator(todoText)).getText();
    }

    @Step("Нажать на чек-бокс выполнения todo")
    public TodoPage clickTodoCheckbox(String todoText) {
        By locator = By.xpath(String.format("//span[contains(text(), '%s')]/preceding-sibling::input", todoText));
        click(locator);
        return this;
    }

    @Step("Нажать кнопку «archive»")
    public TodoPage clickArchiveButton() {
        click(archiveButton);
        return this;
    }

    @Step("Узнать наличие todo на странице")
    public boolean isTodoPresent(String todoText) {
        return !driver.findElements(getTodoLocator(todoText)).isEmpty();
    }

    @Step("Ввести название новой todo")
    public TodoPage setTodoInput(String todoText) {
        inputText(todoInput, todoText);
        return this;
    }

    @Step("Получить текст placeholder поля ввода")
    public String getTodoInputPlaceholder() {
        return waitVisible(todoInput).getAttribute("placeholder");
    }

    @Step("Нажать кнопку add (добавление todo)")
    public TodoPage clickAddButton() {
        click(addButton);
        return this;
    }

    @Step("Получить стиль text-decoration-line для todo")
    public String getTextStyles(String todoText) {
        return waitVisible(getTodoLocator(todoText)).getCssValue("text-decoration-line");
    }

    private By getTodoLocator(String todoText) {
        return By.xpath(String.format("//span[contains(text(), '%s')]", todoText));
    }

    @Step("Навести курсор на кнопку archive")
    public TodoPage hoverToArchiveButton() {
        hoverToElement(archiveButton);
        return this;
    }

    @Step("Получить стиль color кнопки archive")
    public String getColorArchiveButton() {
        return getHexColor(archiveButton, "color");
    }

    @Step("Получить стиль text-decoration-line кнопки archive")
    public String getTextDecorationLineArchiveButton() {
        return waitVisible(archiveButton).getCssValue("text-decoration-line");
    }

    @Step("Навести курсор на кнопку add")
    public TodoPage hoverToAddButton() {
        hoverToElement(addButton);
        return this;
    }

    @Step("Получить стиль background-color кнопки add")
    public String getBackgroundColorAddButton() {
        return getHexColor(addButton, "background-color");
    }
}
