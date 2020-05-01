package ru.yandex.firstTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LaptopsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LaptopsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    private String offers = "/html/body/div[2]/div[5]/div[3]/div[1]/div[2]/div/div[1]/div";
    private String firstOffersTitle = "//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
            "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a";

    public void setMinPrice(String minPrice) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glpricefrom")));
        driver.findElement(By.id("glpricefrom")).sendKeys(minPrice);
    }

    public void setMaxPrice(String maxPrice) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glpriceto")));
        driver.findElement(By.id("glpriceto")).sendKeys(maxPrice);
    }

    public void setManufacturerLenovo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[for=\"7893318_152981\"]")));
        driver.findElement(By.cssSelector("[for=\"7893318_152981\"]")).click();
    }

    public void setManufacturerHp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[for=\"7893318_152722\"]")));
        driver.findElement(By.cssSelector("[for=\"7893318_152722\"]")).click();
    }

    public void waitOffers(int number) {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(offers), number));
    }

    public void setNumberOffers12() {
        driver.findElement(By.xpath("//span//button[@type='button']")).click();
        driver.findElement(By.xpath("//span[@class='select__text' and text()='Показывать по 12']")).click();
    }

    public String nameFirstOffers() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstOffersTitle)));
        return driver.findElement(By.xpath(firstOffersTitle)).getAttribute("title");
    }

    public void inputSearch(String input) {
        driver.findElement(By.id("header-search")).sendKeys(input);
    }

    public void pressFindButton() {
        driver.findElement(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")).click();
    }
}
