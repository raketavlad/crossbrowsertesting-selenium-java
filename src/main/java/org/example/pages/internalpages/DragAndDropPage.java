package org.example.pages.internalpages;

import io.qameta.allure.Step;
import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends BasePage {

    private static final String PATH = "/drag-and-drop.html";

    private final By headline = By.xpath("//h1");
    private final By element = By.cssSelector("div#draggable");
    private final By dragElementText = By.cssSelector("div#draggable p");
    private final By dropArea = By.cssSelector("div#droppable");
    private final By dropAreaText = By.cssSelector("div#droppable p");

    public DragAndDropPage(WebDriver driver) {
        super(driver, PATH);
    }

    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    public String getElementText() {
        return waitVisible(dragElementText).getText();
    }

    @Step("Получить значение текста в DropArea")
    public String getDropAreaText() {
        return waitVisible(dropAreaText).getText();
    }

    @Step("Получить цвет заднего фона DropArea")
    public String getDropAreaBackgroundColor() {
        return getHexColor(dropArea, "background-color");
    }

    @Step("Перетащить элемент")
    public DragAndDropPage dragAndDropElement() {
        Actions action = new Actions(driver);
        action.dragAndDrop(waitVisible(element), waitVisible(dropArea)).perform();
        return this;
    }
}
