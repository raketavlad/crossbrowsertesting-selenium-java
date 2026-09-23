package tests.internalpages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BasePageTest;

public class SeleniumExamplePageTest extends BasePageTest {

    @BeforeMethod
    public void openPage() {
        basePage.open("https://crossbrowsertesting.github.io/selenium_example_page.html");
    }

    @Test
    public void checkHeadline () {
        seleniumExamplePage.checkHeadline();
    }

    @Test
    public void checkIntro() {
        seleniumExamplePage.checkIntro();
    }

    @Test
    public void checkList() {
        seleniumExamplePage.checkTitleList().checkList();
    }

    @Test
    public void checkLink() {
        seleniumExamplePage.checkLink();
    }

    @Test
    public void checkButton() {
        seleniumExamplePage.checkButton();
    }

}
