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
//        1. Открыть браузер и развернуть на весь экран.
        driver.manage().window().maximize();
//        2. Зайти на yandex.ru.
        driver.get("https://yandex.ru/");
    }

    public void goMarket() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(marketLinkLocator));
//        3. Перейти в яндекс маркет
        driver.findElement(marketLinkLocator).click();
        nextWindow();
    }

    public void goComputer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(computerLinkLocator));
        driver.findElement(headerSearch).click();
//        4. Выбрать раздел Компьютеры
        driver.findElement(computerLinkLocator).click();
        nextWindow();
    }

    public void goLaptops() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(laptopsLinkLocator));
//        5. Выбрать раздел Ноутбуки
        driver.findElement(laptopsLinkLocator).click();
        nextWindow();
    }
}
