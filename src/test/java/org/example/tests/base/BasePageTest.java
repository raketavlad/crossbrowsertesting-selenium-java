package org.example.tests.base;

import org.example.common.CommonAction;
import org.example.pages.base.BasePage;
import org.example.pages.internalpages.SeleniumExamplePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BasePageTest {

    protected WebDriver driver = CommonAction.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(driver);

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
