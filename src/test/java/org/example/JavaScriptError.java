package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class JavaScriptError {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/javascript_error");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void captureAndPrintJSError() {
        LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry error : jserrors){
            System.err.println(error.getMessage());
        }
    }




}