package org.example.tests.internalpages;

import org.example.listeners.ScreenshotListener;
import org.example.pages.internalpages.LoginForm;
import org.example.tests.base.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class LoginFormTest extends BasePageTest {

    @Test(description = "Проверка заголовка страницы аутентификации",
            groups = "regress")
    public void checkHeadline() {
        LoginForm page = new LoginForm(getDriver());
        page.open();

        Assert.assertEquals(page.getHeadline(), "Login");
    }

    @Test(description = "Проверка описания страницы аутентификации",
            groups = "regress")
    public void checkDescription() {
        LoginForm page = new LoginForm(getDriver());
        page.open();

        Assert.assertEquals(page.getDescription(), "This is an example login form for Selenium tests.");
    }

    @Test(description = "Проверка аутентификации под существующим пользователем с валидными данными",
            groups = "smoke")
    public void validLoginTest() {
        LoginForm page = new LoginForm(getDriver());
        page.open();

        page.setUsername("tester@crossbrowsertesting.com").setPassword("test123").clickLoginButton();

        Assert.assertEquals(page.getWelcomeHeadline(), "Welcome tester@crossbrowsertesting.com");
        Assert.assertEquals(page.getLoggedInText(), "You are now logged in!");
    }

    @Test(description = "Проверка аутентификации с невалидными данными",
            groups = "regress",
            dataProvider = "testInvalidLogin")
    public void invalidLoginTest(String username, String password) {
        LoginForm page = new LoginForm(getDriver());
        page.open();

        page.setUsername(username).setPassword(password).clickLoginButton();

        Assert.assertEquals(page.getAlertText(), "Username or password is incorrect");

    }

    @DataProvider(name = "testInvalidLogin")
    private Object[][] getData() {
        return new Object[][]{
                {"tester", "test123"},
                {"random_test@mail.ru", "test123"},
                {"tester@crossbrowsertesting.com", "password"}
        };
    }
}
