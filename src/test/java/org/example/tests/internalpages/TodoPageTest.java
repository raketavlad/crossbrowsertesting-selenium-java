package org.example.tests.internalpages;

import org.example.pages.internalpages.TodoPage;
import org.example.tests.base.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TodoPageTest extends BasePageTest {

    @Test(description = "Проверка заголовка",
            groups = "smoke")
    public void checkHeadline() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        Assert.assertEquals(page.getHeadline(), "Todo App");
    }

    @Test(description = "Проверка плейсхолдера в поле ввода",
            groups = "regress")
    public void checkPlaceholder() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        Assert.assertEquals(page.getTodoInputPlaceholder(), "add new todo here");
    }

    @Test(description = "Проверка добавления todo",
            groups = "smoke")
    public void checkAddTodo() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        String todoText = "todoText";
        int countTodo = page.getAllTodoCount();
        page.setTodoInput(todoText).clickAddButton();
        Assert.assertEquals(page.getTodoText(todoText), todoText);
        Assert.assertEquals(page.getAllTodoCount(), ++countTodo);
    }

    @Test(description = "Проверка, что todo выбирается",
            groups = "smoke")
    public void checkSelectedTodo() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        String nameTodo = "Gather";
        int allTodoCount = page.getAllTodoCount();
        int remainingTodoCount = page.getRemainingTodoCount();
        page.clickTodoCheckbox(nameTodo);

        Assert.assertEquals(page.getAllTodoCount(), allTodoCount,
                "Общее количество изменилось, хотя не должно");
        Assert.assertEquals(page.getRemainingTodoCount(), --remainingTodoCount,
                "Количество оставшихся не соответствует ожидаемому (должно уменьшиться на 1)");
        Assert.assertEquals(page.getTextStyles(nameTodo), "line-through");
    }

    @Test(description = "Проверка, что todo возвращается из выполнено",
            groups = "smoke")
    public void checkRejectedTodo() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        String nameTodo = "Gather";
        page.clickTodoCheckbox(nameTodo);
        int allTodoCount = page.getAllTodoCount();
        int remainingTodoCount = page.getRemainingTodoCount();
        page.clickTodoCheckbox(nameTodo);

        Assert.assertEquals(page.getAllTodoCount(), allTodoCount,
                "Общее количество изменилось, хотя не должно");
        Assert.assertEquals(page.getRemainingTodoCount(), ++remainingTodoCount,
                "Количество оставшихся не соответствует ожидаемому (должно уменьшиться на 1)");
        Assert.assertEquals(page.getTextStyles(nameTodo), "none");
    }

    @Test(description = "Проверка отправки в архив",
            groups = "smoke")
    public void checkCompletedTodo() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        String nameTodo = "Gather";
        int allTodoCount = page.getAllTodoCount();

        page.clickTodoCheckbox(nameTodo).clickArchiveButton();
        Assert.assertEquals(page.getAllTodoCount(), --allTodoCount,
                "Общее количество не изменилось, хотя должно уменьшиться на 1");
        Assert.assertFalse(page.isTodoPresent(nameTodo),
                "Отправленный в архив todo не должен быть виден на странице");
    }

    @Test(description = "Проверка стилей кнопки archive без и при hover",
            groups = "regress")
    public void checkStylesArchiveButton() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        Assert.assertEquals(page.getColorArchiveButton(), "#337ab7");
        Assert.assertEquals(page.getTextDecorationLineArchiveButton(), "none");

        page.hoverToArchiveButton();

        Assert.assertEquals(page.getColorArchiveButton(), "#23527c");
        Assert.assertEquals(page.getTextDecorationLineArchiveButton(), "underline");
    }

    @Test(description = "Проверка стилей кнопки add без и при hover",
            groups = "regress")
    public void checkStylesAddButton() {
        TodoPage page = new TodoPage(getDriver());
        page.open();

        Assert.assertEquals(page.getBackgroundColorAddButton(), "#337ab7");
        page.hoverToAddButton();
        Assert.assertEquals(page.getBackgroundColorAddButton(), "#286090");
    }
}
