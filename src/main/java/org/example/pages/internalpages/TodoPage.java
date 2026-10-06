package org.example.pages.internalpages;

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

    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    public int getAllTodoCount() {
        return getCountTodo(ALL_COUNT_INDEX);
    }

    public int getRemainingTodoCount() {
        return getCountTodo(REMAINING_COUNT_INDEX);
    }

    private int getCountTodo(int index) {
        String[] array = waitVisible(todoCounts).getText().split(" ");
        return Integer.parseInt(array[index]);
    }

    public String getTodoText(String todoText) {
        return waitVisible(getTodoLocator(todoText)).getText();
    }

    public TodoPage clickTodoCheckbox(String todoText) {
        By locator = By.xpath(String.format("//span[contains(text(), '%s')]/preceding-sibling::input", todoText));
        click(locator);
        return this;
    }

    public TodoPage clickArchiveButton() {
        click(archiveButton);
        return this;
    }

    public boolean isTodoPresent(String todoText) {
        return !driver.findElements(getTodoLocator(todoText)).isEmpty();
    }

    public TodoPage setTodoInput(String todoText) {
        inputText(todoInput, todoText);
        return this;
    }

    public String getTodoInputPlaceholder() {
        return waitVisible(todoInput).getAttribute("placeholder");
    }

    public TodoPage clickAddButton() {
        click(addButton);
        return this;
    }

    public String getTextStyles(String todoText) {
        return waitVisible(getTodoLocator(todoText)).getCssValue("text-decoration-line");
    }

    private By getTodoLocator(String todoText) {
        return By.xpath(String.format("//span[contains(text(), '%s')]", todoText));
    }

    public TodoPage hoverToArchiveButton() {
        hoverToElement(archiveButton);
        return this;
    }

    public String getColorArchiveButton() {
        return getHexColor(archiveButton, "color");
    }

    public String getTextDecorationLineArchiveButton() {
        return waitVisible(archiveButton).getCssValue("text-decoration-line");
    }

    public TodoPage hoverToAddButton() {
        hoverToElement(addButton);
        return this;
    }

    public String getBackgroundColorAddButton() {
        return getHexColor(addButton, "background-color");
    }
}
