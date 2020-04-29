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
        TransitionPage transitionPage = PageFactory.initElements(driver, TransitionPage.class);

        transitionPage.open();

        transitionPage.goMarket();

        transitionPage.goComputer();

        transitionPage.goLaptops();

        LaptopsPage laptopsPage = PageFactory.initElements(driver, LaptopsPage.class);

        laptopsPage.setPrice();

        laptopsPage.setManufacturer();

        laptopsPage.setNumberOffers();

        laptopsPage.firstOffersVerification();
    }
}
