package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Timer;

import static org.testng.Assert.assertTrue;

public class InfiniteScroll {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void infiniteScroll() {
        boolean footerNote = driver.findElement(By.xpath("//div[contains(text(),'Powered by')]")).isDisplayed();

        try{
            Timer t = new java.util.Timer();
            t.schedule(new java.util.TimerTask(){
                @Override
                public void run(){
                    driver.quit();
                }
            }, 10000);

        // scrolling down
        while(footerNote){
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
        } catch (NoSuchElementException e){
            System.out.println("closed Browser after 10 sec");
        }
    }



}