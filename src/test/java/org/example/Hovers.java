package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Hovers {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void mouseHover() {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='example']//div[3]//img[1]"));
        action.moveToElement(element).build().perform();

        String hoverMessage = driver.findElement(By.xpath("//h5[contains(text(),'name: user3')]")).getText();

        Assert.assertEquals(hoverMessage, "name: user3");
    }



}