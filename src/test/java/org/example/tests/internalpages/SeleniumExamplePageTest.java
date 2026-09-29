package org.example.tests.internalpages;

import org.example.pages.internalpages.SeleniumExamplePage;
import org.example.tests.base.BasePageTest;
import org.testng.annotations.Test;

public class SeleniumExamplePageTest extends BasePageTest {

    @Test(groups = "regress")
    public void checkHeadline() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        seleniumExamplePage.checkHeadline();
    }

    @Test(groups = "regress")
    public void checkIntro() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        seleniumExamplePage.checkIntro();
    }

    @Test(groups = "regress")
    public void checkList() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        seleniumExamplePage.checkTitleList().checkList();
    }

    @Test(groups = "smoke")
    public void checkLink() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        seleniumExamplePage.checkLink();
    }

    @Test(groups = "smoke")
    public void checkButton() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();
        seleniumExamplePage.checkButton();
    }

    @Test(groups = "smoke")
    public void checkForm() {
        SeleniumExamplePage seleniumExamplePage = new SeleniumExamplePage(getDriver());
        seleniumExamplePage.open();

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
