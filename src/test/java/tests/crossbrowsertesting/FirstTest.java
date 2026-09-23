package tests.crossbrowsertesting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class FirstTest extends BaseTest {

    @BeforeMethod
    public void openPage() {
        basePage.open("https://crossbrowsertesting.github.io/selenium_example_page.html");
    }

    @Test
    public void checkHeadline () {
        firstPage.checkHeadline();
    }

    @Test
    public void checkIntro() {
        firstPage.checkIntro();
    }

    @Test
    public void checkList() {
        firstPage.checkTitleList().checkList();
    }

    @Test
    public void checkLink() {
        firstPage.checkLink();
    }

    @Test
    public void checkButton() {
        firstPage.checkButton();
    }

}
