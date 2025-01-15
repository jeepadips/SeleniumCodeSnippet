package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragAndDrop {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test
    public void dragAndDropYellowBox(){
        WebElement yellowBox = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement redBox = driver.findElement(By.xpath("//div[@id='column-b']"));

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(yellowBox)
                .moveToElement(redBox)
                .release(redBox)
                .build();
        dragAndDrop.perform();
    }

}
