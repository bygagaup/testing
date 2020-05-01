package ru.yandex.firstTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.WebDriverSettings;

public class FirstTest extends WebDriverSettings {

    @Test
    public void firstTest() {
        driver.manage().window().maximize();

        TransitionPage transitionPage = PageFactory.initElements(driver, TransitionPage.class);

        transitionPage.open();
        transitionPage.goMarket();
        transitionPage.nextWindow();
        transitionPage.goComputer();
        transitionPage.nextWindow();
        transitionPage.goLaptops();
        transitionPage.nextWindow();

        LaptopsPage laptopsPage = PageFactory.initElements(driver, LaptopsPage.class);

        laptopsPage.setMinPrice("10000");
        laptopsPage.setMaxPrice("30000");
        laptopsPage.setManufacturerLenovo();
        laptopsPage.setManufacturerHp();
        laptopsPage.waitOffers(48);
        laptopsPage.setNumberOffers12();
        laptopsPage.waitOffers(12);
        String nameOffers = laptopsPage.nameFirstOffers();
        laptopsPage.inputSearch(nameOffers);
        laptopsPage.pressFindButton();
        laptopsPage.waitOffers(12);
        Assert.assertEquals(nameOffers, laptopsPage.nameFirstOffers());
    }
}
