package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FloatingMenu {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/floating_menu");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void clickFloatingMenu() {
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();

        // Scrolling to Footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();

        String url = driver.getCurrentUrl();

        Assert.assertEquals(url, "https://the-internet.herokuapp.com/floating_menu#about");

    }





}