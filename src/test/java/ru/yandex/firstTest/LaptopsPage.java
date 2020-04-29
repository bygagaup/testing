package ru.yandex.firstTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaptopsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LaptopsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    private By offers = By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/div[2]/div/div[1]/div");
    private By firstOffers = By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/div[2]/div/div[1]/div[1]");
    private By firstOffersTitle = By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
            "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a");

    public void setPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstOffers));
//        6. Задать параметр «Цена, Р» от 10000 до 30000 рублей.
        driver.findElement(By.id("glpricefrom")).sendKeys("10000");
        driver.findElement(By.id("glpriceto")).sendKeys("30000");
    }

    public void setManufacturer() {
//        7. Выбрать производителя HP и Lenovo
        driver.findElement(By.cssSelector("[for=\"7893318_152981\"]")).click();
        driver.findElement(By.cssSelector("[for=\"7893318_152722\"]")).click();
//        8. Дождаться результатов поиска.
        wait.until(ExpectedConditions.numberOfElementsToBe(offers, 48));
    }

    public void setNumberOffers() {
//        9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы)
        driver.findElement(By.xpath("//span//button[@type='button']")).click();
        driver.findElement(By.xpath("//div//div//div//span[@class='select__text' and text()='Показывать по 12']")).click();
//        10. Дождаться обновления результатов.
        wait.until(ExpectedConditions.numberOfElementsToBe(offers, 12));
//        11. Проверить, что на странице отобразилось 12 элементов.
        Assert.assertEquals(12, driver.findElements(offers).size());
    }

    public void firstOffersVerification() {
//        12. Запомнить наименование первого значения в списке.
        String name = driver.findElement(firstOffersTitle).getAttribute("title");
//        13. В поисковую строку ввести запомненное значение.
        driver.findElement(By.id("header-search")).sendKeys(name);
//        14. Нажать кнопку «Найти»
        driver.findElement(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(offers, 12));
        String name2 = driver.findElement(firstOffersTitle).getAttribute("title");
//        15. Проверить, что наименование товара соответствует запомненному значению.
        System.out.println("name: " + name + "\nname2: " + name2);
        Assert.assertEquals(name, name2);
    }
}
