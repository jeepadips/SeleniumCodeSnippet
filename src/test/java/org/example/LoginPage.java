package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginPage {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void login() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//form[@id='login']//button")).click();

        String headerContent = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(headerContent, "Secure Area");

    }




}