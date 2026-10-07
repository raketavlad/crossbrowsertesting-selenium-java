package org.example.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.example.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        "Screenshot on failure",
                        "image/png",
                        new ByteArrayInputStream(screenshotBytes),
                        "png"
                );
            }
        }
    }
}
