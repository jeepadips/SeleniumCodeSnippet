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

public class ForgotPassword {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void forgotPassword() {
        driver.findElement(By.id("email")).sendKeys("test@mail.com");
        driver.findElement(By.id("form_submit")).click();

        String message = driver.findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(message,"Internal Server Error");
    }
}