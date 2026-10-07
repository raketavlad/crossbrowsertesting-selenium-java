package org.example.pages.internalpages;

import io.qameta.allure.Step;
import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginForm extends BasePage {

    private static final String PATH = "/login-form.html";

    private final By headline = By.xpath("//h2");
    private final By description = By.xpath("//em");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("submit");
    private final By alert = By.cssSelector("div.ng-binding");
    private final By welcomeHeadline = By.cssSelector("h2.ng-binding");
    private final By loggedInText = By.id("logged-in");

    public LoginForm(WebDriver driver) {
        super(driver, PATH);
    }

    public String getHeadline() {
        return waitVisible(headline).getText();
    }

    public String getDescription() {
        return waitVisible(description).getText();
    }

    @Step("Заполнить поле username значением: {0}")
    public LoginForm setUsername(String username) {
        inputText(usernameField, username);
        return this;
    }

    @Step("Заполнить поле password значением: {0}")
    public LoginForm setPassword(String password) {
        inputText(passwordField, password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public LoginForm clickLoginButton() {
        click(loginButton);
        return this;
    }

    @Step("Проверка ошибки при неуспешной аутентификации")
    public String getAlertText() {
        return waitVisible(alert).getText();
    }

    @Step("Проверка заголовка при успешной аутентификации")
    public String getWelcomeHeadline() {
        return waitVisible(welcomeHeadline).getText();
    }

    @Step("Проверка текста при успешной аутентификации")
    public String getLoggedInText() {
        return waitVisible(loggedInText).getText();
    }
}
