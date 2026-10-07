package org.example.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.example.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Map;

public class ScreenshotListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {

        if (result.getStatus() != Status.FAILED && result.getStatus() != Status.BROKEN) {
            return;
        }

        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return;
        }

        byte[] screenshotBytes = captureFullPageScreenshot(driver);
        if (screenshotBytes == null || screenshotBytes.length == 0) {
            return;
        }

        Allure.addAttachment(
                "Screenshot on " + result.getStatus().value(),
                "image/png",
                new ByteArrayInputStream(screenshotBytes),
                "png"
        );
    }

    private byte[] captureFullPageScreenshot(WebDriver driver) {
        // 1. Firefox: встроенный метод
        if (driver instanceof FirefoxDriver) {
            try {
                return ((HasFullPageScreenshot) driver)
                        .getFullPageScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                System.err.println("Firefox full page screenshot failed: " + e.getMessage());
            }
        }

        // 2. Chrome/Edge: через Chrome DevTools Protocol
        if (driver instanceof ChromiumDriver) {
            try {
                Map<String, Object> params = Map.of(
                        "captureBeyondViewport", true,
                        "fromSurface", true
                );
                Object result = ((ChromiumDriver) driver)
                        .executeCdpCommand("Page.captureScreenshot", params);

                if (result instanceof Map) {
                    String base64Data = (String) ((Map<?, ?>) result).get("data");
                    if (base64Data != null) {
                        return Base64.getDecoder().decode(base64Data);
                    }
                }
            } catch (Exception e) {
                System.err.println("CDP full page screenshot failed: " + e.getMessage());
            }
        }

        // 3. Фолбэк: обычный скриншот видимой области
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Fallback screenshot failed: " + e.getMessage());
            return null;
        }
    }
}
