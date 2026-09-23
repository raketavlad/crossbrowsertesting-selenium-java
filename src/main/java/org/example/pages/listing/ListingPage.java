package org.example.pages.listing;

import org.example.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ListingPage extends BasePage {

    private final By headline = By.cssSelector("h1.title__text");
    private final By itemCounter = By.cssSelector("div.products-counter");
    private final By cards = By.cssSelector("div.setout__box");
    private final By inStock = By.cssSelector("div[aria-label='Наличие товаров']");


    public ListingPage(WebDriver driver) {
        super(driver);
    }

    public ListingPage checkHeadline() {
        Assert.assertEquals(driver.findElement(headline).getText(), "Фиксированные коммутаторы");
        return this;
    }

    public ListingPage checkListingItemCount(int expectedItemCount) {

        int countItems = Integer.parseInt(driver.findElement(itemCounter).getText()
                        .replaceAll("\\D", ""));
        Assert.assertEquals(countItems, expectedItemCount);
        return this;
    }

    public ListingPage checkPageItemCount() {
        Assert.assertEquals(driver.findElements(cards).size(), 40);
        return this;
    }
}
