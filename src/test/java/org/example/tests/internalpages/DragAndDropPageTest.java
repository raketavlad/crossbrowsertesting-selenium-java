package org.example.tests.internalpages;

import org.example.pages.internalpages.DragAndDropPage;
import org.example.tests.base.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropPageTest extends BasePageTest {

    @Test(description = "Проверка заголовка страницы DragAndDrop",
            groups = "regress")
    public void checkHeadline() {
        DragAndDropPage page = new DragAndDropPage(getDriver());
        page.open();

        Assert.assertEquals(page.getHeadline(), "Drag and Drop example for Selenium Tests");
    }

    @Test(description = "Проверка DragAndDrop",
            groups = "smoke")
    public void checkDragAndDrop() {
        DragAndDropPage page = new DragAndDropPage(getDriver());
        page.open();

        Assert.assertEquals(page.getElementText(), "Drag me to my target");
        Assert.assertEquals(page.getDropAreaText(), "Drop here");
        Assert.assertEquals(page.getDropAreaBackgroundColor(), "#cccccc");

        page.dragAndDropElement();

        Assert.assertEquals(page.getDropAreaText(), "Dropped!");
        Assert.assertEquals(page.getDropAreaBackgroundColor(), "#fbf9ee");
    }
}
