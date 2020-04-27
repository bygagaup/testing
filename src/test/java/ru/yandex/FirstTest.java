package ru.yandex;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends WebDriverSettings {

    static private void nextWindow(ChromeDriver driver) {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.close();
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Test
    public void firstTest() {

//        1. Открыть браузер и развернуть на весь экран.
        driver.manage().window().maximize();

//        2. Зайти на yandex.ru.
        driver.get("https://yandex.ru/");

        WebDriverWait wait = new WebDriverWait(driver, 30);

        if (driver.findElements(By.cssSelector("[class=\"_2EPSjI-GdM _1PSvaLPzKx BCVQlNQsVv\"]")).size() > 0)
            driver.findElement(By.cssSelector("[class=\"_2EPSjI-GdM _1PSvaLPzKx BCVQlNQsVv\"]")).click();

//        3. Перейти в яндекс маркет
        driver.findElement(By.cssSelector("[href=\"https://market.yandex.ru/?clid=505&utm_source=" +
                "face_abovesearch&utm_campaign=face_abovesearch\"]")).click();
        nextWindow(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoPartMarket")));

//        4. Выбрать раздел Компьютеры
        driver.findElement(By.cssSelector("[href=\"/catalog--kompiuternaia-tekhnika/54425\"]")).click();
        nextWindow(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"_39qdPorEKz\"]")));

//        5. Выбрать раздел Ноутбуки
        driver.findElement(By.cssSelector("[href=\"/catalog--noutbuki/54544/list?hid=91013\"]")).click();
        nextWindow(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"title title_size_32 i-bem " +
                "title_changeable_yes title_js_inited\"]")));

//        6. Задать параметр «Цена, Р» от 10000 до 30000 рублей.
        driver.findElement(By.id("glpricefrom")).sendKeys("10000");
        driver.findElement(By.id("glpriceto")).sendKeys("30000");

//        7. Выбрать производителя HP и Lenovo
        driver.findElement(By.cssSelector("[for=\"7893318_152981\"]")).click();
        driver.findElement(By.cssSelector("[for=\"7893318_152722\"]")).click();

//        8. Дождаться результатов поиска.
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/" +
                "div[2]/div/div[1]/div"), 48));

//        9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы)
        driver.findElement(By.xpath("//span//button[@type='button']")).click();
        driver.findElement(By.xpath("//div//div//div//span[@class='select__text' and text()='Показывать по 12']")).click();

//        10. Дождаться обновления результатов.
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/" +
                "div[2]/div/div[1]/div"), 12));

//        11. Проверить, что на странице отобразилось 12 элементов.
        int webSize = driver.findElements(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/div[2]/div/div[1]/div")).size();
        Assert.assertEquals(12, webSize);

//        12. Запомнить наименование первого значения в списке.
        String name = driver.findElement(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")).getText();

//        13. В поисковую строку ввести запомненное значение.
        driver.findElement(By.id("header-search")).sendKeys(name);

//        14. Нажать кнопку «Найти»
        driver.findElement(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")));

        String name2 = driver.findElement(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")).getText();

//        15. Проверить, что наименование товара соответствует запомненному значению.
        Assert.assertEquals(name, name2);
    }
}
