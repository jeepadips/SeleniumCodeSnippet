package org.example;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class StatusCodes {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://the-internet.herokuapp.com/status_codes");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void statusCode() {
        ArrayList<String> URL = new ArrayList<String>();
        int statuscode;

        // Get all the links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Looping through all the element, finding URL link and adding it to the ArrayList
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            URL.add(href);
        }

        statuscode = RestAssured.get(URL.get(2)).statusCode();
        Assert.assertEquals(statuscode, 200);

        statuscode = RestAssured.given().redirects().follow(false).when().get(URL.get(3)).statusCode();
        Assert.assertEquals(statuscode, 301);

        statuscode = RestAssured.get(URL.get(4)).statusCode();
        Assert.assertEquals(statuscode, 404);

        statuscode = RestAssured.get(URL.get(5)).statusCode();
        Assert.assertEquals(statuscode, 500);
    }




}