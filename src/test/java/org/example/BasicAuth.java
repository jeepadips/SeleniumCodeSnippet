package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicAuth {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test(priority = 1)
    public void sendValidDetails(){
        String successMessage = driver.findElement(By.xpath("//div[@id='content']//div//p")).getText();
        Assert.assertEquals(successMessage, "Congratulations! You must have the proper credentials.");
    }
}
