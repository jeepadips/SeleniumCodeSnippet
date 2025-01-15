package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DisappearingElements {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/disappearing_elements");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test
    public void isGalleryLinkDisplayed(){
        boolean galleryLink = false;

        try{
            galleryLink = driver.findElement(By.xpath("//a[contains(text(),'Gallery')]")).isDisplayed();
        } catch (NoSuchElementException e){
            driver.navigate().refresh();
            galleryLink = driver.findElement(By.xpath("//a[contains(text(),'Gallery')]")).isDisplayed();
        }
        Assert.assertTrue(galleryLink);
    }

}
