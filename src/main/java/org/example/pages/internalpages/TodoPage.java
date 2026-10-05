package org.example.pages.internalpages;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TodoPage extends BasePage {

    private static final String PATH = "/todo-app.html";

    private final By headline = By.cssSelector("h2");
    private final By todoCounts = By.cssSelector("span.ng-binding");

    private final By archiveButon = By.xpath("//a[text()='archive']");
    private final By todoInput = By.cssSelector("input#todotext");
    private final By addButton = By.id("addbutton");


    public TodoPage(WebDriver driver) {
        super(driver, PATH);
    }

    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    // возможно нужно вынести в переменную
    public int getAllTodoCount() {
        return getCountTodo(2);
    }

    // возможно нужно вынести в переменную
    public int getRemainingTodoCount() {
        return getCountTodo(0);
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
        click(archiveButon);
        return this;
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
        return driver.findElement(getTodoLocator(todoText)).getCssValue("text-decoration-line");
    }

    private By getTodoLocator(String todoText) {
        return By.xpath(String.format("//span[contains(text(), '%s')]", todoText));
    }

    public TodoPage hoverToArchiveButton() {
        hoverToElement(archiveButon);
        return this;
    }

    public String getColorArchiveButton() {
//        return driver.findElement(archiveButon).getCssValue("color");
        return getHexColor(archiveButon, "color");
    }

    public String getTextDecorationLineArchiveButton() {
        return driver.findElement(archiveButon).getCssValue("text-decoration-line");
    }

    public TodoPage hoverToAddButton() {
        hoverToElement(addButton);
        return this;
    }

    public String getBackgroundColorAddButton() {
//        return driver.findElement(addButton).getCssValue("background-color");
        return getHexColor(addButton, "background-color");
    }
}
