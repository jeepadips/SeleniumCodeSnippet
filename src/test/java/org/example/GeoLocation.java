package org.example;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class GeoLocation {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {

    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test(priority = 1)
    public void allowGeoLocation() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashedMap<>();
        prefs.put("profile.default_content_setting_values.geolocation",1);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Latitude: ')]")));

        String successContent = driver.findElement(By.xpath("//p[contains(text(),'Latitude: ')]")).getText();
        assertTrue(successContent.contains("See it on Google"));

    }

    @Test(priority = 2)
    public void blockGeolocation() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(10000);
    }





}