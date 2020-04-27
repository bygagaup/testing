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
    public void firstTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://yandex.ru/");

        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Яндекс"));

        driver.findElement(By.cssSelector("[href=\"https://market.yandex.ru/?clid=505&utm_source=" +
                "face_abovesearch&utm_campaign=face_abovesearch\"]")).click();
        nextWindow(driver);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoPartMarket")));

        driver.findElement(By.cssSelector("[href=\"/catalog--kompiuternaia-tekhnika/54425\"]")).click();
        nextWindow(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"_39qdPorEKz\"]")));

        driver.findElement(By.cssSelector("[href=\"/catalog--noutbuki/54544/list?hid=91013\"]")).click();
        nextWindow(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"title title_size_32 i-bem " +
                "title_changeable_yes title_js_inited\"]")));

//        Thread.sleep(15000);
        driver.findElement(By.id("glpricefrom")).sendKeys("10000");
        driver.findElement(By.id("glpriceto")).sendKeys("30000");
        driver.findElement(By.cssSelector("[for=\"7893318_152981\"]")).click();
        driver.findElement(By.cssSelector("[for=\"7893318_152722\"]")).click();


        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/" +
                "div[2]/div/div[1]/div"), 48));


        driver.findElement(By.xpath("//span//button[@type='button']")).click();

        driver.findElement(By.xpath("//div//div//div//span[@class='select__text' and text()='Показывать по 12']")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/" +
                "div[2]/div/div[1]/div"), 12));


        int webSize = driver.findElements(By.xpath("/html/body/div[2]/div[5]/div[3]/div[1]/div[2]/div/div[1]/div")).size();

        Assert.assertTrue(webSize == 12);

        String name = driver.findElement(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")).getText();

        driver.findElement(By.id("header-search")).sendKeys(name);

        driver.findElement(By.cssSelector("button[class=\"_1XiEJDPVpk\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")));

        String name2 = driver.findElement(By.xpath("//div[@class=\"n-snippet-list n-snippet-list_type_vertical metrika " +
                "b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited\"]/div[1]//h3/a")).getText();

        Assert.assertTrue(name.equals(name2));
    }
}
