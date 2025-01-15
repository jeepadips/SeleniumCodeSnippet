package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HorizontalSlider {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test(priority = 1)
    public void clickOnTheSlider() {
        driver.findElement(By.tagName("input")).click();
        String range = driver.findElement(By.id("range")).getText();
        Assert.assertEquals(range, "2.5");
    }

    @Test(priority = 2)
    public void moveSliderUsingKeyboard() {
        WebElement slider = driver.findElement(By.tagName("input"));
        slider.sendKeys(Keys.ARROW_RIGHT);
        String range = driver.findElement(By.id("range")).getText();
        Assert.assertEquals(range, "3");
    }

    @Test(priority = 3)
    public void moveSliderUsingMouse() {
        WebElement slider = driver.findElement(By.tagName("input"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider,60, 0).build().perform();
        String range = driver.findElement(By.id("range")).getText();
        Assert.assertEquals(range, "5");
    }




}