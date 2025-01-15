package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ContextMenu {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void initialSetup() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/context_menu");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


    @Test
    public void performMouseRightClick(){
        WebElement selectArea = driver.findElement(By.xpath("//div[@id='hot-spot']"));

        Actions action = new Actions(driver);
        action.contextClick(selectArea);
        action.build().perform();

        String popupMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(popupMessage, "You selected a context menu");
    }

}
