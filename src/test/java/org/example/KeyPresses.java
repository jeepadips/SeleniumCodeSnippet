package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class KeyPresses {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void keyboardPressEvent() {
        WebElement inputBox = driver.findElement(By.id("target"));
        String result;

        inputBox.sendKeys(Keys.ARROW_DOWN);
        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You entered: DOWN");

        inputBox.sendKeys(Keys.ESCAPE);
        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You entered: ESCAPE");

        inputBox.sendKeys(Keys.BACK_SPACE);
        result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You entered: BACK_SPACE");
    }


}