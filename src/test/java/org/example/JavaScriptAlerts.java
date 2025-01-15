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

public class JavaScriptAlerts {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void handleJsAlert() {
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
        driver.switchTo().alert().accept();

        String resultMessage = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(resultMessage, "You successfully clicked an alert");
    }

    @Test
    public void acceptJsConfirm(){
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
        driver.switchTo().alert().accept();

        String resultMessage = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(resultMessage, "You clicked: Ok");
    }

    @Test
    public void cancelJsConfirm(){
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
        driver.switchTo().alert().dismiss();

        String resultMessage = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(resultMessage, "You clicked: Cancel");
    }

    @Test
    public void handleJsPrompt(){
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        driver.switchTo().alert().sendKeys("I am Jeeva");
        driver.switchTo().alert().accept();

        String resultMessage = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(resultMessage, "You entered: I am Jeeva");
    }




}