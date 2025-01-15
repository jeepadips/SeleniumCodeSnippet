package org.example;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrokenImages {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/broken_images");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    // Using Rest Assured to check Status
    @Test
    public void statusCodeOfImage(){
        int statuscode;
        String imageURL;

        imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[1]"))
                .getAttribute("src");
        statuscode = RestAssured.get(imageURL).statusCode();
        Assert.assertEquals(statuscode, 404);

        imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[2]"))
                .getAttribute("src");
        statuscode = RestAssured.get(imageURL).statusCode();
        Assert.assertEquals(statuscode, 404);

        imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[3]"))
                .getAttribute("src");
        statuscode = RestAssured.get(imageURL).statusCode();
        Assert.assertEquals(statuscode, 200);
    }
}
