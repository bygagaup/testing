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

    public void setPrice() {
        System.out.println(driver.getTitle());
        System.out.println("1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("27903767-tab")));
        System.out.println("2");
//        6. Задать параметр «Цена, Р» от 10000 до 30000 рублей.
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glpricefrom")));
        System.out.println("3");
        try {
            driver.findElement(By.id("glpricefrom")).sendKeys("10000");
        } catch (NoSuchElementException e) {
            System.out.println("Handled NoSuchElementException");
        }
        System.out.println("4");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glpriceto")));
        System.out.println("5");
        try {
            driver.findElement(By.id("glpriceto")).sendKeys("30000");
        } catch (NoSuchElementException e) {
            System.out.println("Handled NoSuchElementException");
        }
        System.out.println("6");
    }

    public void setManufacturer() {
//        7. Выбрать производителя HP и Lenovo
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[for=\"7893318_152981\"]")));
        driver.findElement(By.cssSelector("[for=\"7893318_152981\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[for=\"7893318_152722\"]")));
        driver.findElement(By.cssSelector("[for=\"7893318_152722\"]")).click();
//        8. Дождаться результатов поиска.
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(offers), 48));
    }

    public void setNumberOffers() {
//        9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span//button[@type='button']")));
        driver.findElement(By.xpath("//span//button[@type='button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div//div//span[@class='select__text'" +
                " and text()='Показывать по 12']")));
        driver.findElement(By.xpath("//div//div//div//span[@class='select__text' and text()='Показывать по 12']")).click();
//        10. Дождаться обновления результатов.
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(offers), 12));
//        11. Проверить, что на странице отобразилось 12 элементов.
        Assert.assertEquals(12, driver.findElements(By.xpath(offers)).size());
    }

    public void firstOffersVerification() {
//        12. Запомнить наименование первого значения в списке.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstOffersTitle)));
        String name = driver.findElement(By.xpath(firstOffersTitle)).getAttribute("title");
//        13. В поисковую строку ввести запомненное значение.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-search")));
        driver.findElement(By.id("header-search")).sendKeys(name);
//        14. Нажать кнопку «Найти»
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")));
        driver.findElement(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")).click();
//        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(offers), 12));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstOffersTitle)));
        String name2 = driver.findElement(By.xpath(firstOffersTitle)).getAttribute("title");
//        15. Проверить, что наименование товара соответствует запомненному значению.
        Assert.assertEquals(name, name2);
    }
}
