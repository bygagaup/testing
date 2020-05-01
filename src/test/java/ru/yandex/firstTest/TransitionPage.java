package ru.yandex.firstTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransitionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransitionPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    private By marketLinkLocator = By.cssSelector("[href=\"https://market.yandex.ru/?clid=505&utm_source=" +
            "face_abovesearch&utm_campaign=face_abovesearch\"]");
    private By headerSearch = By.id("header-search");
    private By computerLinkLocator = By.cssSelector("[href=\"/catalog--kompiuternaia-tekhnika/54425\"]");
    private By laptopsLinkLocator = By.cssSelector("[href=\"/catalog--noutbuki/54544/list?hid=91013\"]");

    public void nextWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.close();
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void open() {
        driver.get("https://yandex.ru/");
    }

    public void goMarket() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(marketLinkLocator));
        driver.findElement(marketLinkLocator).click();
    }

    public void goComputer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(computerLinkLocator));
        driver.findElement(headerSearch).click();
        driver.findElement(computerLinkLocator).click();
    }

    public void goLaptops() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(laptopsLinkLocator));
        driver.findElement(laptopsLinkLocator).click();
    }
}
