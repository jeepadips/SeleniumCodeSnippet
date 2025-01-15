package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ABTesting {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/abtest");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test(priority = 1)
    public void WithCookieAfterVisitingPage() {

        String headingText; // Store header text

        // asserting the header test before adding cookie
        headingText = driver.findElement(By.tagName("h3")).getText();
        assertTrue(headingText.contains("A/B Test"));

        driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
        driver.navigate().refresh();

        // asserting the header test before adding cookie
        headingText = driver.findElement(By.cssSelector("h3")).getText();
        assertTrue(headingText.contains("No A/B Test"));
    }

    // setting opt out in URL query params
    @Test(priority = 2)
    public void WithOptOutUrl(){
        driver.get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
        driver.switchTo().alert().dismiss();
        assertTrue(driver.findElement(By.cssSelector("h3")).getText().contains("No A/B Test"));
    }



}