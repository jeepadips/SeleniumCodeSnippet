package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicLoading {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/dynamic_loading");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test
    public void waitTilElementDisplayed(){
        driver.findElement(By.xpath("//a[(text()='Example 1: Element on page that is hidden')]")).click();
        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish")));

        String textValue = driver.findElement(By.xpath("//div[@id='finish']")).getText();
        Assert.assertEquals(textValue, "Hello World!");
    }

    @Test
    public void waitUntilElementRendered(){
        driver.findElement(By.xpath("//a[(text()='Example 2: Element rendered after the fact')]")).click();
        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish")));

        boolean element = driver.findElement(By.id("finish")).isDisplayed();
        Assert.assertTrue(element);
    }


}
