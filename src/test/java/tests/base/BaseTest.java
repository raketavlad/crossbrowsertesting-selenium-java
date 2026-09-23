package tests.base;

import org.example.common.CommonAction;
import org.example.pages.base.BasePage;
import org.example.pages.crossbrowsertesting.FirstPage;
import org.example.pages.listing.ListingPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    protected WebDriver driver = CommonAction.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected ListingPage listingPage = new ListingPage(driver);
    protected FirstPage firstPage = new FirstPage(driver);

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
