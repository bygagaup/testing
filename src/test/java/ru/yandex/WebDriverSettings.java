package ru.yandex;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    public ChromeDriver driver;

    @Before
    public void setUp() {
//        1. Открыть браузер и развернуть на весь экран.
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver81_win32.exe");
        driver = new ChromeDriver();
    }
    @After
    public void close() {
        driver.quit();
    }
}
