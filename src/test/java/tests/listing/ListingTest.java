package tests.listing;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ListingTest extends BaseTest {

    @BeforeClass
    public void openCategoryPage() {
        basePage.open("https://shop.nag.ru/catalog/00001.kommutatory/40213.fiksirovannye-kommutatory");
    }


    @Test
    public void checkHeadline() {
        listingPage.checkHeadline();
    }

    @Test
    public void checkListingItemCount() {
        listingPage.checkListingItemCount(1187);
    }

    @Test
    public void checkPageItemCount() {
        listingPage.checkPageItemCount();
    }



}
