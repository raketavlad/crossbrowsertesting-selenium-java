package org.example.tests.internalpages;

import org.example.pages.internalpages.SeleniumExamplePage;
import org.example.tests.base.BasePageTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumExamplePageTest extends BasePageTest {

    private SeleniumExamplePage seleniumExamplePage;

    @BeforeMethod(alwaysRun = true)
    public void openPage() {
        seleniumExamplePage = new SeleniumExamplePage(driver);
        seleniumExamplePage.open();
    }

    @Test(groups = "regress")
    public void checkHeadline() {
        seleniumExamplePage.checkHeadline();
    }

    @Test(groups = "regress")
    public void checkIntro() {
        seleniumExamplePage.checkIntro();
    }

    @Test(groups = "regress")
    public void checkList() {
        seleniumExamplePage.checkTitleList().checkList();
    }

    @Test(groups = "smoke")
    public void checkLink() {
        seleniumExamplePage.checkLink();
    }

    @Test(groups = "smoke")
    public void checkButton() {
        seleniumExamplePage.checkButton();
    }

    @Test(groups = "smoke")
    public void checkForm() {
        String someText = "Some text";
        String option = "option1";
        String radio = "radio1";
        String textArea = "English texts for beginners to practice reading and comprehension online and for free.";
        seleniumExamplePage.checkTitleForm()
                .checkInputTextPlaceholder()
                .setText(someText)
                .chooseCheckbox(true)
                .selectAnOption(option)
                .selectAnRadio(radio)
                .setTextarea(textArea)
                .pressSubmitButton()
                .checkResultTitle()
                .checkTextResult(someText)
                .checkCheckbox(true)
                .checkSelectedOption(option)
                .checkSelectedRadio(radio)
                .checkTextarea(textArea);
    }
}
