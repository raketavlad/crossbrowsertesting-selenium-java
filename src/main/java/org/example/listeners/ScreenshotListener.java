package org.example.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        Throwable throwable = result.getThrowable();

        String status = (throwable != null && !(throwable instanceof AssertionError)) ? "BROKEN" : "FAILED";
        WebDriver driver = getDriverFromContext(result);

        if (driver != null) {
            // 1. Делаем скриншот как массив байт
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // 2. ГАРАНТИРОВАННОЕ ПРИКРЕПЛЕНИЕ К ALLURE
            // Используем ByteArrayInputStream для красивого отображения в отчете
            Allure.addAttachment(
                    "Screenshot on " + status.toLowerCase(),
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    "png"
            );

            // 3. Сохранение локально (ваш старый рабочий код)
            saveScreenshotLocally(screenshotBytes, testName, timestamp, status);
        }
    }

    private WebDriver getDriverFromContext(ITestResult result) {
        Object driverAttribute = result.getTestContext().getAttribute("driver");
        if (driverAttribute instanceof WebDriver) {
            return (WebDriver) driverAttribute;
        }
        return null;
    }

    // Оптимизированный метод: принимает уже готовые байты, чтобы не делать скриншот дважды
    private void saveScreenshotLocally(byte[] screenshotBytes, String testName, String timestamp, String status) {
        try {
            String folderPath = "./screenshots/" + status + "/";
            String filePath = folderPath + testName + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get(folderPath));
            Files.write(Paths.get(filePath), screenshotBytes);
            System.out.println("Скриншот локально сохранен: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения скриншота локально: " + e.getMessage());
        }
    }
}
