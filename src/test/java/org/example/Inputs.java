package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Inputs {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test(priority = 1)
    public void sendStringValue() {
        WebElement inputBox = driver.findElement(By.tagName("input"));
        inputBox.sendKeys("I am Arum");

        String value = inputBox.getAttribute("value");
        Assert.assertEquals(value, "");

    }

    @Test(priority = 2)
    public void sendNumericalValue() throws InterruptedException {
        WebElement inputBox = driver.findElement(By.tagName("input"));
        inputBox.sendKeys("1234");

        String value = inputBox.getAttribute("value");
        Assert.assertEquals(value, "1234");

    }




}