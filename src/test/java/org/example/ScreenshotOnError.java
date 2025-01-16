package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Timestamp;

import static org.testng.Assert.assertTrue;

public class ScreenshotOnError {
    String myPath = "C:/IntelliJ Projects/SeleniumCodeSnippet/screenshot";
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
            TakesScreenshot screenShot = (TakesScreenshot) driver;
            FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),
                    new File(myPath+ timestamp + ".png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void fullPageScreenshot() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
            TakesScreenshot screenShot = (TakesScreenshot) driver;
            FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),
                    new File(myPath+ timestamp + ".png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Taking the screenshot automatically when the error occurs by checking the status of result after every method has run

    @Test(expectedExceptions = NoSuchElementException.class)
    public void generateException(){
        driver.findElement(By.xpath("element-not-in-the-page")).click();
    }



}