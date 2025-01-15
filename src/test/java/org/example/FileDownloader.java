package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class FileDownloader {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {

        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("download.default_directory",System.getProperty("user.dir"));

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        driver.get("https://admin:admin@the-internet.herokuapp.com/download");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test(priority = 1)
    public void downloadFile() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void verifyFileExists() throws InterruptedException {
        String fileName = driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]")).getText();

        File file = new File("C:/Users/kavic/Downloads/" + fileName);
        Thread.sleep(5000);

        Assert.assertTrue(file.exists());
    }




}
