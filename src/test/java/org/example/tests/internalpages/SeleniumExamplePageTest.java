package org.example.tests.internalpages;

import org.example.pages.internalpages.SeleniumExamplePage;
import org.example.pages.internalpages.SeleniumExamplePageSecond;
import org.example.tests.base.BasePageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumExamplePageTest extends BasePageTest {

    @Test(groups = "regress")
    public void checkHeadline() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

        Assert.assertEquals(seleniumExamplePage.getHeadline(), "Selenium Test Example Page");
    }

    @Test(groups = "regress")
    public void checkIntro() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

        Assert.assertEquals(seleniumExamplePage.getIntro(), "A very basic example page for" +
                " running remote Selenium Tests on the CrossBrowserTesting.com platform.");
    }

    @Test(groups = "regress")
    public void checkList() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

        Assert.assertEquals(seleniumExamplePage.getTitleList(), "Unordered List");
        Assert.assertEquals(seleniumExamplePage.getList(), List.of("One", "Two", "Three", "Four"));
    }

    @Test(groups = "smoke")
    public void checkLink() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

        SeleniumExamplePageSecond secondPage = seleniumExamplePage.clickLink();
        Assert.assertEquals(secondPage.getUrl(),
                "https://crossbrowsertesting.github.io/selenium_example_page2.html");
        Assert.assertEquals(secondPage.getHeadline(), "Selenium Example Page 2");
        Assert.assertEquals(secondPage.getContent(), "I am content on page 2!");
    }

    @Test(groups = "smoke")
    public void checkButton() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        Assert.assertEquals(seleniumExamplePage.checkButton(), "I am the message!!");
    }

    @Test(groups = "smoke")
    public void checkForm() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

        String someText = "Some text";
        String option = "option1";
        String radio = "radio1";
        String textArea = "English texts for beginners to practice reading and comprehension online and for free.";

        Assert.assertEquals(seleniumExamplePage.checkTitleForm(), "Form Elements");
        Assert.assertEquals(seleniumExamplePage.checkInputTextPlaceholder(), "Input Text Here");

        seleniumExamplePage.setText(someText)
                .chooseCheckbox() // Видимо нужно будет переделать, нет проверки на состояние чек-бокса
                .selectAnOption(option)
                .selectAnRadio(radio)
                .setTextarea(textArea)
                .pressSubmitButton();

        Assert.assertEquals(seleniumExamplePage.checkResultTitle(), "Form Results");
        Assert.assertEquals(seleniumExamplePage.checkTextResult(), someText);
        String checkboxResult = seleniumExamplePage.getCheckbox();
        if (checkboxResult != null) {
            Assert.assertEquals(seleniumExamplePage.getCheckbox(), "on");
        }
        Assert.assertEquals(seleniumExamplePage.checkSelectedOption(), option);
        Assert.assertEquals(seleniumExamplePage.checkSelectedRadio(), radio);
        Assert.assertEquals(seleniumExamplePage.checkTextarea(), textArea);
    }
}
